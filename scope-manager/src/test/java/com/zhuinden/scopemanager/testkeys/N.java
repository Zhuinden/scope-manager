package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class N
        extends ChildKey {
    private final K k;

    public N(K k) {
        super("N");
        this.k = k;
    }

    @Override
    public Object parent() {
        return k;
    }
}
