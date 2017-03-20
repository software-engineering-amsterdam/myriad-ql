package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by casboot, 14-02-17.
 */
public class BooleanExpression extends Expression {

    public BooleanExpression(String expr) {
        super(expr);
        setValue(new Boolean(false));
    }

    private Boolean evaluateBool() throws ScriptException {
        String expr = computableExpression();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Boolean(engine.eval(expr).toString());
    }

    public Boolean getValue() {
        return (Boolean) value;
    }

    @Override
    void initId(String id, String value) {
        if (Boolean.isBoolean(value))
            ids.computeIfAbsent(id, k -> new Boolean(value));
        else
            ids.computeIfAbsent(id, k -> new Number(value));
    }

    @Override
    public String toString() {
        evaluate();
        return value == null ? "" : "" + value;
    }

    @Override
    public void evaluate() {
        try {
            value = evaluateBool();
            stringValueProperty.set(value.toString());
        } catch (ScriptException e) {
            stringValueProperty.set("");
        }
    }
}

