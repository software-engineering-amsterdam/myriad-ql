package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import javax.script.ScriptException;

public class Identifier extends Literal{

    public Identifier(int lineNumber, String id) {
        super(lineNumber, id);
    }

    @Override
    public String evaluateExpression(EnvironmentsTable environmentsTable) {
        return environmentsTable.find(getId());
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
