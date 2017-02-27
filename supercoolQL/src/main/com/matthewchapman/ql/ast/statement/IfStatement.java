package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.QLExpression;
import com.matthewchapman.ql.ast.QLStatement;
import com.matthewchapman.ql.validation.validator.Visitor;

import java.util.ArrayList;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement extends QLStatement {

    private final ArrayList<QLStatement> statements;
    private QLExpression expression;

    public IfStatement(QLExpression e, ArrayList<QLStatement> s) {
        this.statements = new ArrayList<>();
        this.addStatements(s);
        this.expression = e;
    }

    public void addStatements(ArrayList<QLStatement> statements) {
        for (QLStatement s : statements) {
            this.statements.add(s);
        }
    }

    public void setExpression(QLExpression e) {
        this.expression = e;
    }

    public ArrayList<QLStatement> getStatements()
    {
        return this.statements;
    }

    public QLExpression getExpression()
    {
        return this.expression;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
