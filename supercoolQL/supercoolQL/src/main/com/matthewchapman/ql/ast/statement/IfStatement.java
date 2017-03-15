package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.validation.QLVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 21/02/2017.
 * <p>
 * Contains ifCaseStatements to be evaluated if the condition contained evaluates to true.
 */

public class IfStatement extends Statement {

    private final List<Statement> ifCaseStatements;
    private Expression condition;

    public IfStatement(Expression e, List<Statement> s, int line, int column) {
        this.ifCaseStatements = new ArrayList<>();
        this.addStatements(s);
        this.condition = e;
        this.setLine(line);
        this.setColumn(column);
    }

    public void addStatements(List<Statement> statements) {
        for (Statement s : statements) {
            this.ifCaseStatements.add(s);
        }
    }

    public List<Statement> getIfCaseStatements() {
        return this.ifCaseStatements;
    }

    public Expression getCondition() {
        return this.condition;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }

}
