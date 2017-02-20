package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by casboot on 14-02-17.
 */
public class BooleanExpression implements Expression {

    private String expr;

    public BooleanExpression(String expr) {
        this.expr = expr;
    }

    @Override
    public Number evaluateNumber() throws Exception {
        return null;
    }

    @Override
    public Boolean evaluateBool() throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Boolean(engine.eval(expr).toString());
    }

    @Override
    public String toString() {
        try {
            Boolean n = evaluateBool();
            return "" + n.getValue();
        } catch (Exception e) {
            return e.toString();
        }
    }
}

