package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 08/03/2017.
 */
public class StringType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return type.getType().equals("string");
    }

    @Override
    public String getType() {
        return "string";
    }
}
