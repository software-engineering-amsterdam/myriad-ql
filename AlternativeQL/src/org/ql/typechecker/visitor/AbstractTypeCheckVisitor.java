package org.ql.typechecker.visitor;

import org.ql.ast.ASTVisitor;
import org.ql.ast.Expression;
import org.ql.ast.Statement;

import java.util.List;

public abstract class AbstractTypeCheckVisitor<T, C> implements ASTVisitor<T, C> {

    protected T visitExpression(Expression condition, C context) {
        return condition.accept(this, context);
    }

    protected T visitStatements(List<Statement> statements, C context) {
        for (Statement statement : statements) {
            statement.accept(this, context);
        }

        return null;
    }
}
