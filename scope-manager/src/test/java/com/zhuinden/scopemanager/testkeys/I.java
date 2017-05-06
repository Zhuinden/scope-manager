package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class I
        extends ChildKey {
    private final B b;

    public I(B b) {
        super("I");
        this.b = b;
    }

    @Override
    public Object parent() {
        return b;
    }
}
