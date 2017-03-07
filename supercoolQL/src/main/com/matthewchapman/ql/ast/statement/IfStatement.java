package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.validation.QLVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 21/02/2017.
 *
 * Contains statements to be evaluated if the condition contained evaluates to true.
 */

public class IfStatement extends Statement {

    private final List<Statement> statements;
    private Expression condition;

    public IfStatement(Expression e, List<Statement> s) {
        this.statements = new ArrayList<>();
        this.addStatements(s);
        this.condition = e;
    }

    public void addStatements(List<Statement> statements) {
        for (Statement s : statements) {
            this.statements.add(s);
        }
    }


    public List<Statement> getStatements() {
        return this.statements;
    }

    public Expression getCondition() {
        return this.condition;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
