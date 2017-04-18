package org.uva.hatt.taxform.ast.nodes.types;

import org.uva.hatt.taxform.ast.nodes.ASTNode;


public abstract class ValueType extends ASTNode {

    ValueType(int lineNumber) {
        super(lineNumber);
    }

    public java.lang.String name(){
        return "Unknown";
    }

    public java.lang.Boolean isBoolean(){ return false; }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
