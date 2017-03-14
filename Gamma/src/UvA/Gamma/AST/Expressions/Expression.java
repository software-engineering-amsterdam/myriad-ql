package UvA.Gamma.AST.Expressions;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.Validation.IdNotFoundException;
import UvA.Gamma.Validation.IncompatibleTypesException;
import UvA.Gamma.Validation.Validator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
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
    protected Value value;

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

    public Value getValue() {
        return value;
    }

    public void accept(Validator validator) throws IdNotFoundException, IncompatibleTypesException {
        for (String id : getIds()) {
            validator.validateId(id);
            validator.validateIdentifierType(id, value.getType());
        }
    }

    public boolean isDependentOn(String id) {
        return Arrays.stream(getIds()).anyMatch(i -> i.equals(id));
    }

    private String[] getIds() {
        return ids.keySet().toArray(new String[0]);
    }

    public StringProperty getStringValueProperty() {
        return this.stringValueProperty;
    }

    protected String computableExpression() {
        String parsedExpr = expr;
        for (Map.Entry<String, Value> entry : ids.entrySet()) {
            if (entry.getValue() != null) {
                parsedExpr = parsedExpr.replaceFirst(entry.getKey(), entry.getValue().computableString());
            }
        }
        return parsedExpr;
    }

    protected void parseExpression() {
        // add the ID's to the map
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(expr);

        while (matcher.find()) {
            if (matcher.group().matches("^(?i)(true|false)$")) continue;
            ids.put(matcher.group(), null);
        }
    }

    abstract void evaluate();

    abstract void initId(String id, String value);

    public abstract String toString();

}

