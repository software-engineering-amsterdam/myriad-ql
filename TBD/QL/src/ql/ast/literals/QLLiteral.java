package ql.ast.literals;

import ql.ast.Expr;

/**
 * Created by Erik on 7-2-2017.
 */
public interface QLLiteral<T> extends Expr {

    T toValue();
}
