package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


//Created by Tjarco, 14-02-17.


public class NumberExpression extends Expression {

    protected Number value;


    public NumberExpression(String expr) {
        super(expr);
    }

    private Number evaluateNumber() throws ScriptException {
        String expr = computableExpression();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Number(engine.eval(expr).toString());
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        evaluate();
        if (value == null) {
            return "";
        }

        if (value.isInteger()) {
            return "" + value.intValue();
        } else {
            return "" + value.doubleValue();
        }

    }

    @Override
    void initId(String id, String value) {
        ids.computeIfAbsent(id, k -> new Number(value));
    }

    @Override
    public void evaluate() {
        try {
            value = evaluateNumber();
            stringValueProperty.set(value.toString());
        } catch (ScriptException e) {
            /* one or more identifiers have no value yet and therefore the expression cannot be computed */
            stringValueProperty.set("");
        }
    }
}

