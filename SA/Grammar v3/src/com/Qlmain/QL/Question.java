package com.Qlmain.QL;

import com.Qlmain.types_Of_Expr.Expression;

/**
 * Created by matt on 20/02/2017.
 */
public class Question implements Statement<Question>, Node {

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
    public void visitst(Question st) {
        System.out.println("hiiii " + this.name);
    }
}
