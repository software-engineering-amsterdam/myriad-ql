package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 08/03/2017.
 */
public class IntegerType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return type.getType().equals("integer");
    }

    @Override
    public String getType() {
        return "integer";
    }
}
