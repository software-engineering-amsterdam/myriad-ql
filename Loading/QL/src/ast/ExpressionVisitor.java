package ast;

import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.AddExpression;
import ast.expression.AndExpression;
import ast.expression.DivExpression;
import ast.expression.EqExpression;
import ast.expression.GEqExpression;
import ast.expression.GExpression;
import ast.expression.IdExpression;
import ast.expression.LEqExpression;
import ast.expression.LExpression;
import ast.expression.MinusExpression;
import ast.expression.MulExpression;
import ast.expression.NEqExpression;
import ast.expression.NotExpression;
import ast.expression.OrExpression;
import ast.expression.PlusExpression;
import ast.expression.SubExpression;

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
}
