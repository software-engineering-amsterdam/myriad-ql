package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.validation.Visitor;

import java.util.ArrayList;

/**
 * Created by matt on 20/02/2017.
 */
public class Form extends ASTNode implements Visitable{

    private final ArrayList<Statement> statements;
    private final String formName;

    public Form (String name, ArrayList<Statement> statements) {
        this.formName = name;
        this.statements = statements;
    }

    public ArrayList<Statement> getStatements() {
        return this.statements;
    }

    public String getName() {
        return formName;
    }

    public void accept(Visitor visitor) {
        visitor.visitForm(this);
    }

}
