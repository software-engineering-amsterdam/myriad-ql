package UvA.Gamma.AST;

import UvA.Gamma.GUI.MainScreen;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed implements FormItem{
    private String label;
    private String id;
    private String type;
    private String expression;

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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


    public void evaluateExpression(){
        //evaluate the expression and return the result
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
