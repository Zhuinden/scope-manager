package com.zhuinden.scopemanager;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public abstract class ChildKey
        extends TestKey
        implements ScopeManager.Child {
    public ChildKey(String name) {
        super(name);
    }
}
