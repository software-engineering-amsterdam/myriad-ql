package com.matthewchapman.ql.ast;


import java.util.List;

/**
 * Created by matt on 20/02/2017.
 * <p>
 * Base Form class. Acts as root note for the QL AST, contains all statements and a string identifier.
 */
public class Form extends TreeNode {

    private final List<Statement> statements;
    private final String formName;

    public Form(String name, List<Statement> statements) {
        this.formName = name;
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public String getName() {
        return formName;
    }

    @Override
    public String toString() {

        String result = ("form " + formName + "{\n");

        for (Statement statement : this.getStatements()) {
            result = result + statement;
        }

        return result;

    }


}
