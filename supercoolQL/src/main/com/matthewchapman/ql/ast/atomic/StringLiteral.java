package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 24/02/2017.
 *
 * String literal type implementation.
 */
public class StringLiteral extends Type {

    private String value;

    public boolean isLeaf = true;

    public StringLiteral() {
        this.value = "string";
    }

    public String getType() {
        return this.value;
    }

    @Override
    public boolean isCompatible(Type type) {
        return type.getType() == "boolean";
    }

}
