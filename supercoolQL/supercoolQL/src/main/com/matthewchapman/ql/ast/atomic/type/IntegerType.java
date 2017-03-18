package com.matthewchapman.ql.ast.atomic.type;

import com.matthewchapman.ql.ast.atomic.Type;

/**
 * Created by matt on 08/03/2017.
 */
public class IntegerType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return "integer".equals(type.toString());
    }

    @Override
    public String toString() {
        return "integer";
    }
}
