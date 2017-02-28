package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 24/02/2017.
 */
public class IntegerLiteral {

    private final Integer value;

    public IntegerLiteral(Integer i)
    {
        this.value = i;
    }

    public Integer getValue()
    {
        return this.value;
    }

}
