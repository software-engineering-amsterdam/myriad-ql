package com.matthewchapman.ql.ast.atomic.type;

import com.matthewchapman.ql.ast.atomic.Type;

/**
 * Created by matt on 08/03/2017.
 */
public class StringType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return "string".equals(type.toString());
    }

    @Override
    public String toString() {
        return "string";
    }
}
