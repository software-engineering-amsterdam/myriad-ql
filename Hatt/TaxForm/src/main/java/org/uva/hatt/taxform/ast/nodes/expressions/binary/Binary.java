package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

public abstract class Binary{ //extends Expression{
    private Expression lhs;
    private Expression rhs;

    public Binary(int lineNumber, Expression lhs, Expression rhs){
        //super(lineNumber);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression getLhs(){
        return lhs;
    }

    public Expression getRhs(){
        return rhs;
    }
}
