package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by rico on 14-2-17.
 */
public interface UnOp extends Expr {
    Expr getExpr();
}
