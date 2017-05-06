package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class L
        extends ChildKey {
    private final K k;

    public L(K k) {
        super("L");
        this.k = k;
    }

    @Override
    public Object parent() {
        return k;
    }
}
