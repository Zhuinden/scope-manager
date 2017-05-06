package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.CompositeChildKey;
import com.zhuinden.simplestack.HistoryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class K
        extends CompositeChildKey {
    private final C c;

    public K(C c) {
        super("K");
        this.c = c;
    }

    @Override
    public Object parent() {
        return c;
    }

    @Override
    public List<?> keys() {
        return new ArrayList<>(HistoryBuilder.from(new L(this), new M(this), new N(this)).build());
    }
}
