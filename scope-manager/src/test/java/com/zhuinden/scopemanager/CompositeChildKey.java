package com.zhuinden.scopemanager;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public abstract class CompositeChildKey
        extends TestKey
        implements ScopeManager.Child, ScopeManager.Composite {
    public CompositeChildKey(String name) {
        super(name);
    }
}
