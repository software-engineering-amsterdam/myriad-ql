package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Money extends ValueType {

    public Money(int lineNumber) {
        super(lineNumber);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
