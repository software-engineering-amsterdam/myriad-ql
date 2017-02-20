package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;

/**
 * Created by Erik on 14-2-2017.
 */
public interface ASTVisitor<T> {
    T visit(Form node);
    T visit(Statements node);
    T visit(If node);
    T visit(Question node);
    T visit(QLIdent node);
    T visit(QLBoolean node);
    T visit(QLInt node);
    T visit(QLString node);
    T visit(QLFloat node);
    T visit(Add node);
    T visit(Div node);
    T visit(Eq node);
    T visit(GEq node);
    T visit(GT node);
    T visit(LEq node);
    T visit(LT node);
    T visit(Mul node);
    T visit(NEq node);
    T visit(Sub node);
    T visit(Or node);
    T visit(And node);
    T visit(Pos node);
    T visit(Neg node);
    T visit(Not node);


}
