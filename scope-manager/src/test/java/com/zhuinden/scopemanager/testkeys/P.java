package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class P
        extends ChildKey {
    private final M m;

    public P(M m) {
        super("P");
        this.m = m;
    }

    @Override
    public Object parent() {
        return m;
    }
}
