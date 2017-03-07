package QL.ast;

import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.AddExpression;
import QL.ast.expression.AndExpression;
import QL.ast.expression.DivExpression;
import QL.ast.expression.EqExpression;
import QL.ast.expression.GEqExpression;
import QL.ast.expression.GExpression;
import QL.ast.expression.IdExpression;
import QL.ast.expression.LEqExpression;
import QL.ast.expression.LExpression;
import QL.ast.expression.MinusExpression;
import QL.ast.expression.MulExpression;
import QL.ast.expression.NEqExpression;
import QL.ast.expression.NotExpression;
import QL.ast.expression.OrExpression;
import QL.ast.expression.PlusExpression;
import QL.ast.expression.SubExpression;

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
