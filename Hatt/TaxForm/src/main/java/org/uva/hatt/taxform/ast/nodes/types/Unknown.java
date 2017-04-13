package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Unknown extends ValueType{

    public Unknown() {
        super(0);
    }

    @Override
    public java.lang.String name(){
        return "Unknown";
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
