package com.zhuinden.scopemanager;

import com.zhuinden.servicetree.ServiceTree;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public interface ScopeKey {
    void bindServices(ServiceTree.Node node);
}
