package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 27/02/2017.
 *
 * Boolean literal type implementation
 */
public class BooleanLiteral extends BooleanType {

    private String value;

    public BooleanLiteral() {
        this.value = "boolean";
    }

}
