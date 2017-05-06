package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class J
        extends ChildKey {
    private final I i;

    public J(I i) {
        super("J");
        this.i = i;
    }

    @Override
    public Object parent() {
        return i;
    }
}
