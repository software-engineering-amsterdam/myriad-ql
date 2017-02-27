package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.QLType;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 27/02/2017.
 */
public class BooleanLiteral extends QLType {

    public BooleanLiteral(String s)
    {
        super(s);
    }

    public void accept(Visitor visitor) {
        visitor.visitBooleanLiteral(this);
    }
}
