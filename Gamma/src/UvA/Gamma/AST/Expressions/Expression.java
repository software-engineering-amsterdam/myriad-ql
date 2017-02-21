package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Values.Value;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tjarco, 14-02-17.
 */
public abstract class Expression implements ASTNode {
    protected String expr;
    protected SimpleStringProperty stringValueProperty;
    protected Map<String, Value> ids;

    public Expression(String expr) {
        this.expr = expr;
        this.stringValueProperty = new SimpleStringProperty("");
        this.ids = new HashMap<>();
        parseExpression();
        evaluate();
    }

    public boolean idChanged(String id, String value) {
        if (ids.containsKey(id)) {
            initId(id, value); // If the ID has not yet received a value
            ids.get(id).setValue(value);
            evaluate();
            return true;
        }
        return false;
    }

    public StringProperty getStringValueProperty() {
        return this.stringValueProperty;
    }

    protected String computableExpression() {
        String parsedExpr = expr;
        try {
            for (Map.Entry<String, Value> entry : ids.entrySet()) {
                parsedExpr = parsedExpr.replaceAll(entry.getKey(), entry.getValue().computableString());
            }
        } catch (NullPointerException ex) {
            //Not all values are known
        }
        return parsedExpr;
    }

    protected void parseExpression() {
        // add the ID's to the map
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(expr);

        while (matcher.find()) {
            ids.put(matcher.group(), null);
        }
    }

    abstract void evaluate();

    abstract void initId(String id, String value);


    public abstract String toString();

}

