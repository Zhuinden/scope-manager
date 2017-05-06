package com.zhuinden.scopemanager;

import java.util.List;

/**
 * Created by Zhuinden on 2017.05.06..
 */

public interface NestedKey
        extends ScopeKey {
    String NESTED_STACK = "NESTED_STACK";

    List<?> initialKeys();
}
