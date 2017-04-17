package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.nodes.FormVisitor;

public class Unknown extends ValueType{

    public Unknown() {
        super(0);
    }

    @Override
    public java.lang.String name(){
        return "Unknown";
    }

    public <T> T accept(FormVisitor<T> visitor){
        return visitor.visit(this);
    }
}
