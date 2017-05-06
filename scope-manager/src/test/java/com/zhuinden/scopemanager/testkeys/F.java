package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class F
        extends ChildKey {
    private final D d;

    public F(D d) {
        super("F");
        this.d = d;
    }

    @Override
    public Object parent() {
        return d;
    }
}
