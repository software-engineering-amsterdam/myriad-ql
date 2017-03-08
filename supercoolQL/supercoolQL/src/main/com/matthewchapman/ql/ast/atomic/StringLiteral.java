package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 24/02/2017.
 *
 * String literal type implementation.
 */
public class StringLiteral extends StringType {

    private String value;

    public StringLiteral() {
        this.value = "string";
    }

}
