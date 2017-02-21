package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Number;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Created by Tjarco, 14-02-17.


public class NumberExpression implements Expression {

    private String expr;
    private Number value;
    private SimpleStringProperty stringValueProperty;
    private Map<String, Number> ids;

    public NumberExpression(String expr) {
        this.expr = expr;
        this.stringValueProperty = new SimpleStringProperty("");
        this.ids = new HashMap<>();
        parseExpression();
        evaluate();
    }

    private void parseExpression() {
        // add the ID's to the map
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(expr);

        while (matcher.find()) {
            ids.put(matcher.group(), new Number(0));
        }
    }

    private Number evaluateNumber() throws Exception {
        String parsedExpr = expr;
        for (Map.Entry<String, Number> entry : ids.entrySet()) {
            parsedExpr = parsedExpr.replaceAll(entry.getKey(), entry.getValue().toString());
        }

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return new Number(engine.eval(parsedExpr).toString());
    }

    public Number getValue() {
        return value;
    }

    @Override
    public void idChanged(String id, String value) {
        if (ids.containsKey(id)) {
            ids.get(id).setValue(value);
            evaluate();
        }
    }

    @Override
    public StringProperty getStringValueProperty() {
        return this.stringValueProperty;
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
    public void evaluate() {
        try {
            value = evaluateNumber();
            stringValueProperty.set(value.toString());
        } catch (Exception e) {
            /* one or more identifiers have no value yet and therefore the expression cannot be computed */
            stringValueProperty.set("");
        }
    }
}

