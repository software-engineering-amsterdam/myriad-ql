package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 24/02/2017.
 *
 * Integer Literal type implementation
 */
public class IntegerLiteral extends IntegerType {

    private String value;

    public IntegerLiteral() {
        this.value = "integer";
    }

}
