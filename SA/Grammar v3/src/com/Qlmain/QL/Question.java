package com.Qlmain.QL;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement {

    public String name;
    public Expr type;
    public String text;
    public int line;

    public Question(String name, String text, Expr type, int line)
    {
        this.name = name;
        this.text = text;
        this.type = type;
        this.line = line;
    }
}
