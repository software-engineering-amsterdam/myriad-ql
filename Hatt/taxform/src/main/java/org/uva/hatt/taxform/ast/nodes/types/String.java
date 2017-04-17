package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.nodes.FormVisitor;

public class String extends ValueType{

    public String(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public java.lang.String name(){
        return "String";
    }

    public <T> T accept(FormVisitor<T> visitor){
        return visitor.visit(this);
    }
}
