package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;

public abstract class AstVisitor<T> {

    public abstract T visit(Form form);
    public abstract T visit(FormId formId);
    public abstract T visit(Question question);
    public abstract T visit(ValueType valueType);

    public T visit(ASTNode node) {
        return visit(node);
    }

//    public T visit(ASTNode node) {
//        Object astNode = null;
//
//        if (node instanceof Form) {
//            astNode = visit((Form) node);
//        } else if (node instanceof FormId) {
//            astNode = visit((FormId) node);
//        } else if (node instanceof Question) {
//            astNode = visit((Question) node);
//        } else if (node instanceof ValueType) {
//            astNode = visit((ValueType) node);
//        }
//
//        return (T) astNode;
//    }

//    public T visit(ASTNode node) {
//        if (node instanceof Form) {
//            return visit((Form) node);
//        } else if (node instanceof FormId) {
//            return visit((FormId) node);
//        } else if (node instanceof Question) {
//            return visit((Question) node);
//        } else if (node instanceof ValueType) {
//            return visit((ValueType) node);
//        }
//    }

}
