package org.ql.typechecker.visitor;

import org.ql.ast.ASTVisitor;
import org.ql.ast.Expression;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;

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
