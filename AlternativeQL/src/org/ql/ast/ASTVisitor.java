package org.ql.ast;

import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.StatementVisitor;

public interface ASTVisitor<T, C> extends FormVisitor<T, C>, StatementVisitor<T, C>,
        ExpressionVisitor<T, C> {
}
