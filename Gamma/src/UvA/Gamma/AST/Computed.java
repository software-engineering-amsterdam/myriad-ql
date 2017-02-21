package UvA.Gamma.AST;

import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed implements FormItem {
    private String label;
    private String id;
    private String type;
    private Expression expression;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void idChanged(String id, String value) {
        expression.idChanged(id, value);
    }


    @Override
    public StringProperty getStringValueProperty() {
        return expression.getStringValueProperty();
    }

    @Override
    public void show(MainScreen screen) {
        screen.showComputed(this);
    }

    @Override
    public String toString() {
        return "<Computed>: " + label + " " + id + ": " + type + " = " + expression;
    }
}
