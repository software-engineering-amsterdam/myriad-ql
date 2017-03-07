package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.visitors.Visitor;

import javax.script.ScriptException;

public class IntegerLiteral extends Literal{

    public IntegerLiteral(int lineNumber, String id) {
        super(lineNumber, id);
    }

    @Override
    public String evaluateExpression() {
        String eval= null;

        try {
            eval =  evaluate(this.getId());
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return eval;
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
