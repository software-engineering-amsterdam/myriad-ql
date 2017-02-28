package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 24/02/2017.
 */
public class StringLiteral extends Atomic {

    private final String content;

    public StringLiteral(String s)
    {
        this.content = s;
    }

    public String getContent()
    {
        return this.content;
    }

}
