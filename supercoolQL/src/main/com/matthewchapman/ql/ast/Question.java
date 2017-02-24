package com.matthewchapman.ql.ast;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement {

    public String name;
    public Type type;
    public String text;

    public Question(String name, String text, Type type)
    {
        this.name = name;
        this.text = text;
        this.type = type;
    }
}
