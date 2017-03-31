package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.lang.*;

public class Boolean extends ValueType {

    public Boolean(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public java.lang.String name(){
        return "Boolean";
    }

    @Override
    public java.lang.Boolean isBoolean(){ return true; }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
