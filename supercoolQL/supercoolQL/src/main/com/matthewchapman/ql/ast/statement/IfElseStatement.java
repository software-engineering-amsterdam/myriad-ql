package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.validation.visitor.QLVisitor;

import java.util.List;

/**
 * Created by matt on 21/02/2017.
 * <p>
 * If statement with additional storage for statements to be executed if the Expression returns false.
 */

public class IfElseStatement extends IfStatement {

    private final List<Statement> elseCaseStatements;

    public IfElseStatement(Expression e, List<Statement> ifCases, List<Statement> elseCases, int line, int column) {

        super(e, ifCases, line, column);
        this.elseCaseStatements = elseCases;
    }

    @Override
    public String toString() {
        String result = "if " + this.getCondition() + " {\n";

        for(Statement statement:this.getIfCaseStatements()) {
            result = result + "\t" + statement + "\n";
        }

        result = result + "} else {\n";

        for(Statement statement:this.getElseCaseStatements()) {
            result = result + "\t" + statement + "\n";
        }

        return result +"}\n";
    }

    public List<Statement> getElseCaseStatements() {
        return this.elseCaseStatements;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
