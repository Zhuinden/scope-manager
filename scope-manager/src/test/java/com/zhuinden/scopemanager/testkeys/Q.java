package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class Q
        extends ChildKey {
    private final M m;

    public Q(M m) {
        super("Q");
        this.m = m;
    }

    @Override
    public Object parent() {
        return m;
    }
}
