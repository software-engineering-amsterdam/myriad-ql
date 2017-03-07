package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.validation.QLVisitor;

import java.util.List;

/**
 * Created by matt on 21/02/2017.
 *
 * If statement with additional storage for statements to be executed if the Expression returns false.
 */

public class IfElseStatement extends Statement {

    private final List<Statement> ifCaseStatements;
    private final List<Statement> elseCaseStatements;
    private Expression condition;

    public IfElseStatement(Expression e, List<Statement> ifCases, List<Statement> elseCases) {

        this.ifCaseStatements = ifCases;
        this.elseCaseStatements = elseCases;
        this.condition = e;
    }


    public void setCondition(Expression e) {
        this.condition = e;
    }

    public List<Statement> getIfCaseStatements() {
        return this.ifCaseStatements;
    }

    public List<Statement> getElseCaseStatements() {
        return this.elseCaseStatements;
    }

    public Expression getCondition() {
        return this.condition;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
