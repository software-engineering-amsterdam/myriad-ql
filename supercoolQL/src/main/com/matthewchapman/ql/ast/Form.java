package com.matthewchapman.ql.ast;


import java.util.ArrayList;

/**
 * Created by matt on 20/02/2017.
 */
public class Form extends TreeNode {

    private final ArrayList<Statement> statements;
    private final String formName;

    public Form(String name, ArrayList<Statement> statements) {
        this.formName = name;
        this.statements = statements;
    }

    public ArrayList<Statement> getStatements() {
        return this.statements;
    }

    public String getName() {
        return formName;
    }


}
