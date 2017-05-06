package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.CompositeChildKey;
import com.zhuinden.simplestack.HistoryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class M
        extends CompositeChildKey {
    private final K k;

    public M(K k) {
        super("M");
        this.k = k;
    }

    @Override
    public Object parent() {
        return k;
    }

    @Override
    public List<?> keys() {
        return new ArrayList<>(HistoryBuilder.from(new O(this), new P(this), new Q(this)).build());
    }
}
