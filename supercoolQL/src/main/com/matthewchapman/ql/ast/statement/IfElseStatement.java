package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.validation.validator.QLVisitor;

import java.util.ArrayList;

/**
 * Created by matt on 21/02/2017.
 */

public class IfElseStatement extends Statement {

    private final ArrayList<Statement> ifCaseStatements;
    private final ArrayList<Statement> elseCaseStatements;
    private Expression expression;

    public IfElseStatement(Expression e, ArrayList<Statement> ifCases, ArrayList<Statement> elseCases) {

        this.ifCaseStatements = ifCases;
        this.elseCaseStatements = elseCases;
        this.expression = e;
    }


    public void setExpression(Expression e) {
        this.expression = e;
    }

    public ArrayList<Statement> getIfCaseStatements()
    {
        return this.ifCaseStatements;
    }
    public ArrayList<Statement> getElseCaseStatements() { return this.elseCaseStatements;}

    public Expression getExpression()
    {
        return this.expression;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
