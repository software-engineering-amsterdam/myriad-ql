package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.Values.Money;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Tjarco, 21-02-17.
 */
public class MoneyExpression extends NumberExpression {

    public MoneyExpression(String expr) {
        super(expr);
        this.value = new Money(0);
    }

    private Money evaluateMoney() throws ScriptException {
        String expr = computableExpression();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Money(engine.eval(expr).toString());
    }

    @Override
    public void evaluate() {
        try {
            value = evaluateMoney();
            stringValueProperty.set(value.toString());
        } catch (ScriptException e) {
            /* one or more identifiers have no value yet and therefore the expression cannot be computed */
            stringValueProperty.set("");
        }
    }

    @Override
    void initId(String id, String value) {
        ids.computeIfAbsent(id, k -> new Money(value));
    }
}
