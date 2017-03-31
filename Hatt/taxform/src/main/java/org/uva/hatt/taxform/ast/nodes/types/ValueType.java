package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.lang.*;

public abstract class ValueType extends ASTNode {

    ValueType(int lineNumber) {
        super(lineNumber);
    }

    public java.lang.String name(){
        return "Unknown";
    }

    public java.lang.Boolean isBoolean(){ return false; }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
