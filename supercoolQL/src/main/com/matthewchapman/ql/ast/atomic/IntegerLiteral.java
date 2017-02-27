package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.QLType;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 24/02/2017.
 */
public class IntegerLiteral extends QLType {

    public IntegerLiteral(String s)
    {
        super(s);
    }

    public void accept(Visitor visitor) {
        visitor.visitIntegerLiteral(this);
    }
}
