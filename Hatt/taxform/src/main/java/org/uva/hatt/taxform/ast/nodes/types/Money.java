package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Money extends ValueType {

    public Money(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public java.lang.String name(){
        return "Money";
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
