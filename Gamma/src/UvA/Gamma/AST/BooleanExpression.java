package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Boolean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by casboot on 14-02-17.
 */
public class BooleanExpression implements Expression {

    private String expr;
    private Boolean value;

    private SimpleStringProperty stringValueProperty;


    public BooleanExpression(String expr) {
        this.expr = expr;
    }


    private Boolean evaluateBool() throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Boolean(engine.eval(expr).toString());
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void idChanged(String id, String value) {

    }

    @Override
    public StringProperty getStringValueProperty() {
        return this.stringValueProperty;
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

    @Override
    public void evaluate() {
        try {
            value = evaluateBool();
            stringValueProperty.set(value.toString());
        } catch (Exception e) {
            e.printStackTrace();
            stringValueProperty.set("");
        }
    }
}

