package ast;

import ast.atom.BoolAtom;
import ast.atom.EmptyAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.*;
import ast.type.Type;

public interface ExpressionVisitor<T> {

    T visit(AddExpression expr);
    T visit(AndExpression expr);
    T visit(DivExpression expr);
    T visit(EqExpression expr);
    T visit(GEqExpression expr);
    T visit(GExpression expr);
    T visit(IdExpression expr);
    T visit(LEqExpression expr);
    T visit(LExpression expr);
    T visit(MinusExpression expr);
    T visit(MulExpression expr);
    T visit(NEqExpression expr);
    T visit(NotExpression expr);
    T visit(OrExpression expr);
    T visit(PlusExpression expr);
    T visit(SubExpression expr);
 
    T visit(BoolAtom expr);
    T visit(IntegerAtom expr);
    T visit(StringAtom expr);
    T visit(EmptyAtom expr);
}
