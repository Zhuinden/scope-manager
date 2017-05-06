/*
 * Copyright 2017 Gabor Varadi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhuinden.scopemanager;

import android.support.annotation.NonNull;

import com.zhuinden.servicetree.ServiceTree;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.BackstackManager;
import com.zhuinden.simplestack.Bundleable;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.statebundle.StateBundle;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class ScopeManager {
    public interface Child {
        Object parent();
    }

    public interface Composite {
        List<?> keys();
    }

    private final ServiceTree serviceTree;

    private final List<Object> activeKeys = new ArrayList<>();

    public ScopeManager(StateBundle stateBundle) {
        this.serviceTree = new ServiceTree();
        if(stateBundle == null) {
            stateBundle = new StateBundle();
        }
        serviceTree.registerRootService(SERVICE_STATES, stateBundle);
    }

    public static final String SERVICE_STATES = "SERVICE_BUNDLE";

    public StateBundle persistStates() {
        final StateBundle serviceStates = new StateBundle();
        serviceTree.traverseTree(ServiceTree.Walk.PRE_ORDER, new ServiceTree.Walk() {
            @Override
            public void execute(@NonNull ServiceTree.Node node, @NonNull CancellationToken cancellationToken) {
                StateBundle keyBundle = new StateBundle();
                for(ServiceTree.Node.Entry entry : node.getBoundServices()) {
                    if(entry.getService() instanceof Bundleable) {
                        keyBundle.putParcelable(entry.getName(), ((Bundleable) entry.getService()).toBundle());
                    }
                }
                serviceStates.putParcelable(node.getKey().toString(), keyBundle);
            }
        });
        return serviceStates;
    }

    public void setupServices(StateChange stateChange) {
        setupServices(stateChange, false);
    }

    public void setupServices(StateChange stateChange, boolean isFromCompositeKey) {
        final StateBundle states = serviceTree.getRootService(SERVICE_STATES);
        for(Object previousKey : stateChange.getPreviousState()) {
            if(!stateChange.getNewState().contains(previousKey)) {
                activeKeys.remove(previousKey);
                if(!isFromCompositeKey) {
                    ServiceTree.Node previousNode = serviceTree.getNode(previousKey);
                    serviceTree.traverseSubtree(previousNode, ServiceTree.Walk.POST_ORDER, new ServiceTree.Walk() {
                        @Override
                        public void execute(@NonNull ServiceTree.Node node, @NonNull CancellationToken cancellationToken) {
                            states.remove(node.getKey().toString());
                        }
                    });
                    serviceTree.removeNodeAndChildren(previousNode);
                }
            }
        }
        for(Object newKey : stateChange.getNewState()) {
            activeKeys.remove(newKey);
            if(newKey == stateChange.topNewState()) {
                activeKeys.add(newKey);
            }
            if(!isFromCompositeKey) {
                buildServices(newKey);
            }
        }
    }

    private void buildServices(Object newKey) {
        if(!serviceTree.hasNodeWithKey(newKey)) {
            ServiceTree.Node node;
            if(!(newKey instanceof Child)) {
                node = serviceTree.createRootNode(newKey);
            } else {
                Object parentKey = ((Child) newKey).parent();
                if(!serviceTree.hasNodeWithKey(parentKey)) {
                    buildServices(parentKey);
                }
                node = serviceTree.createChildNode(serviceTree.getNode(parentKey), newKey);
            }
            buildServicesForKey(newKey, node);
        }
    }

    private void buildServicesForKey(Object newKey, ServiceTree.Node node) {
        if(newKey instanceof ScopeKey) {
            ((ScopeKey) newKey).bindServices(node);
        }
        restoreServiceStateForKey(node);
        if(newKey instanceof Composite) {
            for(Object nestedKey : ((Composite) newKey).keys()) {
                ServiceTree.Node nestedNode = serviceTree.createChildNode(node, nestedKey);
                buildServicesForKey(nestedKey, nestedNode);
            }
        }
        if(newKey instanceof NestedKey) {
            Backstack nestedStack = serviceTree.getNode(newKey).<BackstackManager>getService(NestedKey.NESTED_STACK).getBackstack();
            for(Object childKey : nestedStack.getInitialParameters()) {
                buildServices(childKey);
            }
        }
    }

    private void restoreServiceStateForKey(ServiceTree.Node node) {
        StateBundle states = serviceTree.getRootService(SERVICE_STATES);
        StateBundle keyBundle = states.getParcelable(node.getKey().toString());
        if(keyBundle != null) {
            List<ServiceTree.Node.Entry> entries = node.getBoundServices();
            for(ServiceTree.Node.Entry entry : entries) {
                if(entry.getService() instanceof Bundleable) {
                    ((Bundleable) entry.getService()).fromBundle(keyBundle.<StateBundle>getParcelable(entry.getName()));
                }
            }
        }
    }

    public ServiceTree getServiceTree() {
        return serviceTree;
    }

    public boolean handleBack(final Backstack backstack, final ServiceTree serviceTree) {
        Object lastKey = activeKeys.get(activeKeys.size() - 1);
        class Cancellation {
            private boolean cancelled;
        }
        final Cancellation cancellation = new Cancellation();
        serviceTree.traverseChain(serviceTree.getNode(lastKey), new ServiceTree.Walk() {
            @Override
            public void execute(@NonNull ServiceTree.Node node, @NonNull CancellationToken cancellationToken) {
                Object _key = node.getKey();
                if(_key instanceof NestedKey) { // ROOT is defined by Activity's TAG
                    NestedKey key = (NestedKey) _key;
                    BackstackManager backstackManager = serviceTree.getNode(key).getService(NestedKey.NESTED_STACK);
                    if(backstackManager.getBackstack().goBack()) {
                        cancellation.cancelled = true;
                        cancellationToken.cancel();
                    }
                }
            }
        });
        if(cancellation.cancelled) {
            return true;
        }
        return backstack.goBack();
    }
}