package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by casboot, 14-02-17.
 */
public class BooleanExpression extends Expression {

    public BooleanExpression(String expr) {
        super(expr);
        this.value = new Boolean(false);
    }

    private Boolean evaluateBool() throws ScriptException {
        String expr = computableExpression();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Boolean(engine.eval(expr).toString());
    }

    @Override
    protected String computableExpression() {
        String expr = super.computableExpression();

        //Default all unknown values to false
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(expr);
        while (matcher.find()) {
            if (matcher.group().matches("^(?i)(true|false)$")) continue;
            expr = expr.replaceFirst(matcher.group(), "false");
        }

        return expr;
    }

    @Override
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
        return value == null ? "" : value.toString();
    }

    @Override
    public void evaluate() {
        try {
            value = evaluateBool();
            // Setting the stringValueProperty will update the value on the UI
            stringValueProperty.set(value.toString());
        } catch (ScriptException e) {
            stringValueProperty.set("");
        }
    }
}

