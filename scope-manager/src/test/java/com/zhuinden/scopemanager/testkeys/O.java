package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class O
        extends ChildKey {
    private final M m;

    public O(M m) {
        super("O");
        this.m = m;
    }

    @Override
    public Object parent() {
        return m;
    }
}
