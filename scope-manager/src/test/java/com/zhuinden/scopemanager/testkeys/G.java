package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.ChildKey;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class G
        extends ChildKey {
    private final D d;

    public G(D d) {
        super("G");
        this.d = d;
    }

    @Override
    public Object parent() {
        return d;
    }
}
