package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Integer extends ValueType {

    public Integer(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public java.lang.String name(){
        return "Integer";
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
