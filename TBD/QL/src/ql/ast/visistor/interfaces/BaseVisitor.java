package ql.ast.visistor.interfaces;

import ql.ast.*;

/**
 * Created by Erik on 20-3-2017.
 */
public interface BaseVisitor<T> {
    T visit(Form node);


    T visit(Statements node);

    T visit(If node);

    T visit(IfElse node);

    T visit(Question node);

    T visit(QuestionExpr node);
}
