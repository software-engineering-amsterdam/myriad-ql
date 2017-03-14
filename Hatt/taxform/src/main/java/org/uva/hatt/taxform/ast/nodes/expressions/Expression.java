package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.Visitor;
import org.uva.hatt.taxform.utils.ScriptEngineUtils;

import javax.script.ScriptException;

public class Expression extends ASTNode{

    private Expression left;
    private Expression right;
    private String operator;

    public Expression(int lineNumber) {
        super(lineNumber);
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String evaluateExpression(EnvironmentsTable environmentsTable) {
        return "(" + this.left.evaluateExpression(environmentsTable) + operator + this.right.evaluateExpression(environmentsTable) + ")";
    }

    public String evaluate(EnvironmentsTable environmentsTable) throws ScriptException {
        return evaluate(evaluateExpression(environmentsTable));
    }

    protected String evaluate(String exp) throws ScriptException {
        return ScriptEngineUtils.evaluate(exp);
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
