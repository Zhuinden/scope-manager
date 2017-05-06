package com.zhuinden.scopemanager.testkeys;

import com.zhuinden.scopemanager.CompositeChildKey;
import com.zhuinden.simplestack.HistoryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public class D
        extends CompositeChildKey {
    private final A a;

    public D(A a) {
        super("D");
        this.a = a;
    }

    @Override
    public Object parent() {
        return a;
    }

    @Override
    public List<?> keys() {
        return new ArrayList<>(HistoryBuilder.from(new E(this), new F(this), new G(this)).build());
    }
}
