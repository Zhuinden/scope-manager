package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class H
        extends ChildKey {
    private final F f;

    public H(F f) {
        super("H");
        this.f = f;
    }

    @Override
    public Object parent() {
        return f;
    }
}
