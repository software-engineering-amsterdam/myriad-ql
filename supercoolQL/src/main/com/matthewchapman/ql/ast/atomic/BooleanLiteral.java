package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 27/02/2017.
 */
public class BooleanLiteral extends Type<String> {

    public BooleanLiteral(String s)
    {
        super(s);
    }

}
