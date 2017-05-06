package com.zhuinden.scopemanager;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public abstract class CompositeKey
        extends TestKey
        implements ScopeManager.Composite {
    public CompositeKey(String name) {
        super(name);
    }
}
