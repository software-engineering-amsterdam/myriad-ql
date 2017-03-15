package QL.ast;

import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.*;

public interface ExpressionVisitor<T> {

    T visit(AddExpr expr);
    T visit(AndExpr expr);
    T visit(DivExpr expr);
    T visit(EqExpr expr);
    T visit(GEqExpr expr);
    T visit(GExpr expr);
    T visit(IdExpr expr);
    T visit(LEqExpr expr);
    T visit(LExpr expr);
    T visit(MinusExpr expr);
    T visit(MulExpr expr);
    T visit(NEqExpr expr);
    T visit(NotExpr expr);
    T visit(OrExpr expr);
    T visit(PlusExpr expr);
    T visit(SubExpr expr);
 
    T visit(BoolAtom expr);
    T visit(IntegerAtom expr);
    T visit(StringAtom expr);
}
