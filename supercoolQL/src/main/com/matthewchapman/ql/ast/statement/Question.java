package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement {

    public final String name;
    public final Type type;
    public final String text;

    public Question(String name, String text, Type type)
    {
        this.name = name;
        this.text = text;
        this.type = type;
    }
}
