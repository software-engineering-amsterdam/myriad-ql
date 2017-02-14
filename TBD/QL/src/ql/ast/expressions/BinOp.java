package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by Erik on 14-2-2017.
 */
public interface BinOp extends Expr {
    Expr getLeft();
    Expr getRight();
}
