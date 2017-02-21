package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.BooleanExpression;
import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Condition implements FormItem {
    private List<FormItem> formItems;
    /* can be empty if no elseBlock is specified */
    private List<FormItem> elseBlockItems;
    private BooleanExpression expression;
    private MainScreen screen;

    public Condition() {
        this.formItems = new ArrayList<>();
        this.elseBlockItems = new ArrayList<>();
    }

    public List<FormItem> getFormItems() {
        return formItems;
    }

    public List<FormItem> getElseBlockItems() {
        return elseBlockItems;
    }

    public void addFormItem(FormItem item) {
        this.formItems.add(item);
    }

    public void addElseBlockItem(FormItem item) {
        this.elseBlockItems.add(item);
    }

    public void setExpression(BooleanExpression expression) {
        this.expression = expression;
    }

    public boolean evaluateExpression() {
        expression.evaluate();
        return expression.getValue() != null && expression.getValue().getValue();
    }

    @Override
    public StringProperty getStringValueProperty() {
        return null; //This item is not intended to be shown by itself
    }

    @Override
    public void idChanged(Form root, String id, String value) {
        expression.idChanged(id, value);
        screen.showCondition(this);
        formItems.forEach(item -> item.idChanged(root, id, value));
        elseBlockItems.forEach(item -> item.idChanged(root, id, value));
    }

    @Override
    public void show(MainScreen screen) {
        this.screen = screen;
        screen.showCondition(this);
    }

    @Override
    public String toString() {
        return "<Condition>: " + expression;
    }
}
