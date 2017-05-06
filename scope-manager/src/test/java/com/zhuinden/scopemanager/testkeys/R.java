package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class R
        extends ChildKey {
    private final Q q;

    public R(Q q) {
        super("R");
        this.q = q;
    }

    @Override
    public Object parent() {
        return q;
    }
}
