package ql.gui.evaluator;

import ql.gui.elements.*;

/**
 * Created by Erik on 20-3-2017.
 */
public interface BaseEvaluator<T> {
    T visit(GUIForm node);
    T visit(GUIStatements node);
    T visit(GUIIf node);
    T visit(GUIIfElse node);
    T visit(GUIQuestion node);
    T visit(GUIQuestionExpr node);
}
