package UvA.Gamma.AST;

import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Condition implements  FormItem {
    private List<FormItem> formItems;
    private String expression;


    public Condition() {
        this.formItems = new ArrayList<>();
    }

    public List<FormItem> getFormItems() {
        return formItems;
    }

    public void addFormItem(FormItem item){
        this.formItems.add(item);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public StringProperty getStringValueProperty() {
        return null;
    }

    @Override
    public boolean hasID(String id) {
        Predicate<FormItem> predicate = e -> e.hasID(id);
        return formItems.stream().anyMatch(predicate);
    }

    @Override
    public void show(MainScreen screen) {
        screen.showCondtion(this);
    }
}
