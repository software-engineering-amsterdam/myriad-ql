package org.uva.hatt.taxform.ast.nodes.types;

public class Money extends ValueType {

    public Money(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public java.lang.String name(){
        return "Money";
    }

    public <T> T accept(TypeVisitor<T> visitor){
        return visitor.visit(this);
    }
}
