package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.QLType;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 24/02/2017.
 */
public class StringLiteral extends QLType {

    public StringLiteral(String s)
    {
        super(s);
    }

    public void accept(Visitor visitor) {
        visitor.visitStringLiteral(this);
    }

}
