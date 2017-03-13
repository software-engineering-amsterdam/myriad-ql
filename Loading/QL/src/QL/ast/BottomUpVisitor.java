//package QL.ast;
//
//import QL.ast.atom.BoolAtom;
//import QL.ast.atom.IntegerAtom;
//import QL.ast.atom.StringAtom;
//import QL.ast.expression.*;
//import QL.ast.type.BooleanType;
//import QL.ast.type.IntegerType;
//import QL.ast.type.StringType;
//
//public abstract class BottomUpVisitor implements VerifyExpressions, TypeVisitor {
//
//    @Override
//    public void visit(AddExpr expr) {
//        expr.accept(this);
//        expr.getLhs().accept(this);
//        expr.getRhs().accept(this);
//    }
//
//    @Override
//    public void visit(AndExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(DivExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(EqExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(GEqExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(GExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(IdExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(LEqExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(LExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(MinusExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(MulExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(NEqExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(NotExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(OrExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(PlusExpr expr) {
//        expr.accept(this);
//    }
//
//    @Override
//    public void visit(SubExpr expr) {
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
