package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 24/02/2017.
 */
public class StringLiteral extends Type<String> {

    public StringLiteral(String s)
    {
        super(s);
    }

}
