package ast;

import ast.expression.*;

public interface ExpressionVisitor {

    void visit(AddExpression expr);
    void visit(AndExpression expr);
    void visit(DivExpression expr);
    void visit(EqExpression expr);
    void visit(GEqExpression expr);
    void visit(GExpression expr);
    void visit(IdExpression expr);
    void visit(LEqExpression expr);
    void visit(LExpression expr);
    void visit(MinusExpression expr);
    void visit(MulExpression expr);
    void visit(NEqExpression expr);
    void visit(NotExpression expr);
    void visit(OrExpression expr);
    void visit(PlusExpression expr);
    void visit(SubExpression expr);
}
