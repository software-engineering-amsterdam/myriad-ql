package ast;

import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.*;
import ast.type.Type;

public interface ExpressionVisitor {

    Type visit(AddExpression expr);
    Type visit(AndExpression expr);
    Type visit(DivExpression expr);
    Type visit(EqExpression expr);
    Type visit(GEqExpression expr);
    Type visit(GExpression expr);
    Type visit(IdExpression expr);
    Type visit(LEqExpression expr);
    Type visit(LExpression expr);
    Type visit(MinusExpression expr);
    Type visit(MulExpression expr);
    Type visit(NEqExpression expr);
    Type visit(NotExpression expr);
    Type visit(OrExpression expr);
    Type visit(PlusExpression expr);
    Type visit(SubExpression expr);

    Type visit(BoolAtom expr);
    Type visit(IntegerAtom expr);
    Type visit(StringAtom expr);
}
