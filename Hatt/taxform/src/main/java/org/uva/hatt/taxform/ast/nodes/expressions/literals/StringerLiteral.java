package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import javax.script.ScriptException;

public class StringerLiteral extends Literal{

    public StringerLiteral(int lineNumber, String id) {
        super(lineNumber, id);
    }

    @Override
    public String evaluateExpression(EnvironmentsTable environmentsTable) {
        String eval= null;

        try {
            eval =  evaluate(this.getId());
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return "\"" + eval + "\"";
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
