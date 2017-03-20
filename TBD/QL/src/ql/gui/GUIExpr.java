package ql.gui;

import ql.ast.ASTNode;
import ql.visistor.interfaces.ExpressionVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public interface GUIExpr {
    <T> T accept(GUIEvaluator<T> visitor);
}
