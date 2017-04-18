package org.uva.hatt.taxform.ast.nodes.types;

public class Unknown extends ValueType{

    public Unknown() {
        super(0);
    }

    @Override
    public java.lang.String name(){
        return "Unknown";
    }

    public <T> T accept(TypeVisitor<T> visitor){
        return visitor.visit(this);
    }
}
