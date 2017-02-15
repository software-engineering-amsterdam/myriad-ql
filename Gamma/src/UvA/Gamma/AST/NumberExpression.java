package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


//Created by Tjarco, 14-02-17.


public class NumberExpression implements Expression {

    private String expr;

    public NumberExpression(String expr) {
        this.expr = expr;
    }

    @Override
    public Number evaluateNumber() throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Number(engine.eval(expr).toString());
    }

    @Override
    public String toString() {
        try {
            Number n = evaluateNumber();

            if (n.isInteger()) {
                return "" + n.intValue();
            } else {
                return "" + n.doubleValue();
            }
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Boolean evaluateBool() throws Exception {
        return null;
    }
}

