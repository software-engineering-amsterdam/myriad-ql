package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by rico on 14-2-17.
 */
public interface NumOp extends Expr {
    Expr getLeft();
    Expr getRight();
}
