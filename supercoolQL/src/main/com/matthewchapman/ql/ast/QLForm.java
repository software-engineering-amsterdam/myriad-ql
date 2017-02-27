package com.matthewchapman.ql.ast;


import java.util.ArrayList;

/**
 * Created by matt on 20/02/2017.
 */
public class QLForm extends ASTNode {

    private final ArrayList<QLStatement> statements;
    private final String formName;

    public QLForm(String name, ArrayList<QLStatement> statements) {
        this.formName = name;
        this.statements = statements;
    }

    public ArrayList<QLStatement> getStatements() {
        return this.statements;
    }

    public String getName() {
        return formName;
    }


}
