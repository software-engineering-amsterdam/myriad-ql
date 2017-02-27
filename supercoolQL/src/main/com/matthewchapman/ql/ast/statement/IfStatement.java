package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Visitable;
import com.matthewchapman.ql.validation.Visitor;

import java.util.ArrayList;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement extends Statement implements Visitable{

    private final ArrayList<Statement> statements;
    private Expression expression;

    public IfStatement(Expression e, ArrayList<Statement> s) {
        this.statements = new ArrayList<>();
        this.addStatements(s);
        this.expression = e;
    }

    public void addStatements(ArrayList<Statement> statements) {
        for (Statement s : statements) {
            this.statements.add(s);
        }
    }

    public void setExpression(Expression e) {
        this.expression = e;
    }

    public ArrayList<Statement> getStatements()
    {
        return this.statements;
    }

    public Expression getExpression()
    {
        return this.expression;
    }

    public void accept(Visitor visitor) {
        visitor.visitIfStatement(this);
    }

}
