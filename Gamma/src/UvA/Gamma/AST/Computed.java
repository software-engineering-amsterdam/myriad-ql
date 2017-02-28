package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.Expression;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed implements FormItem {
    private String label;
    private String id;
    private Value type;
    public Expression expression;

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

    public void setType(Value type) {
        this.type = type;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void idChanged(Form root, String id, String value) {
        if (expression.idChanged(id, value)) {
            root.idChanged(this.id, this.expression.toString());
        }
    }

    @Override
    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    @Override
    public Value[] getValuesForIds() {
        return new Value[]{type};
    }

    @Override
    public String[] getReferencedIds() {
        return expression.getIds();
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
