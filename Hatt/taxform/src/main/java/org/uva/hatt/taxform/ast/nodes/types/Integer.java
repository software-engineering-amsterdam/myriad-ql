package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Integer extends ValueType {

    public Integer(int lineNumber) {
        super(lineNumber);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
