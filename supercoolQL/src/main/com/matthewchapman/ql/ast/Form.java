package com.matthewchapman.ql.ast;

import java.util.ArrayList;

/**
 * Created by matt on 20/02/2017.
 */
public class Form extends Node{

    private ArrayList<Statement> statements;
    private String formName;

    public Form (String name, ArrayList<Statement> statements) {
        this.formName = name;
        this.statements = statements;
    }

    public ArrayList<Statement> getStatements()
    {
        return this.statements;
    }

    public String getName(){return formName;}

}
