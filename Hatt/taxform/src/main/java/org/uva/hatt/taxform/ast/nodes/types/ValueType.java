package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public abstract class ValueType extends ASTNode {

    ValueType(int lineNumber) {
        super(lineNumber);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
