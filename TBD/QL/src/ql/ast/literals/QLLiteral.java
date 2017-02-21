package ql.ast.literals;

import ql.ast.Expr;
import ql.ast.values.Value;

/**
 * Created by Erik on 7-2-2017.
 */
public interface QLLiteral extends Expr {

    Value toValue();
}
