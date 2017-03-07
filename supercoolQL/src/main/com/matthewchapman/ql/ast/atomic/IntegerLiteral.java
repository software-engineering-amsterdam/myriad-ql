package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 24/02/2017.
 *
 * Integer Literal type implementation
 */
public class IntegerLiteral extends Type {

    private String value;

    public IntegerLiteral() {
        this.value = "integer";
    }

    public String getType() {
        return this.value;
    }

    @Override
    public boolean isCompatible(Type type) {
        return type.getType() == "integer";
    }

}
