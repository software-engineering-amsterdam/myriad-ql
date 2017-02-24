package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Node;

/**
 * Created by matt on 24/02/2017.
 */
public class StringLiteral extends Expression{

    private String content;

    public StringLiteral(String s)
    {
        this.content = s;
    }

    public String getContent()
    {
        return this.content;
    }

}
