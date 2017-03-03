package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 27/02/2017.
 *
 * Boolean literal type implementation
 */
public class BooleanLiteral extends Type {

    private String value;

    public boolean isLeaf = true;

    public BooleanLiteral() {
        this.value = "boolean";
    }

    public String getType() {
        return this.value;
    }

    @Override
    public boolean isCompatible(Type type) {
        return type.getType() == "boolean";
    }

}
