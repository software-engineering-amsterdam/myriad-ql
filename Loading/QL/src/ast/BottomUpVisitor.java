//package ast;
//
//import ast.atom.BoolAtom;
//import ast.atom.IntegerAtom;
//import ast.atom.StringAtom;
//import ast.expression.*;
//import ast.type.BooleanType;
//import ast.type.IntegerType;
//import ast.type.StringType;
//
//public abstract class BottomUpVisitor implements ExpressionVisitor, TypeVisitor {
//
//    @Override
//    public void visit(AddExpression expr) {
//        expr.accept(this);
//        expr.getLhs().accept(this);
//        expr.getRhs().accept(this);
//    }
//
//    @Override
//    public void visit(AndExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(DivExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(EqExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(GEqExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(GExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(IdExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(LEqExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(LExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(MinusExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(MulExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(NEqExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(NotExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(OrExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(PlusExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(SubExpression expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(BoolAtom atom) {
//        atom.accept(this);
//    }
//
//    @Override
//    public void visit(StringAtom atom) {
//        atom.accept(this);
//    }
//
//    @Override
//    public void visit(IntegerAtom atom) {
//        atom.accept(this);
//    }
//
//    @Override
//    public void visit(BooleanType type) {
//        type.accept(this);
//    }
//
//    @Override
//    public void visit(StringType type) {
//        type.accept(this);
//    }
//
//    @Override
//    public void visit(IntegerType type) {
//        type.accept(this);
//    }
//
//
//}
