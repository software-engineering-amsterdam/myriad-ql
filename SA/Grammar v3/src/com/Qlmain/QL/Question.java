package com.Qlmain.QL;

import com.Qlmain.typesOfExpr.Expression;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement implements Node {

    public String name;
    public Expression type;
    public String text;
    public int line;

    public Question(String name, String text, Expression type, int line)
    {
        this.name = name;
        this.text = text;
        this.type = type;
        this.line = line;
    }

    @Override
    public boolean isQuestion() {return true;}
}
