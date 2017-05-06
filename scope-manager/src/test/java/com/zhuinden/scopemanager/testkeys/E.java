package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class E
        extends ChildKey {
    private final D d;

    public E(D d) {
        super("E");
        this.d = d;
    }

    @Override
    public Object parent() {
        return d;
    }
}
