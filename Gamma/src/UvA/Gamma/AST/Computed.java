package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.Expression;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.*;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed implements FormItem {
    private String label;
    private String id;
    public Expression expression;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(Value value) {
        this.expression.setValue(value);
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void idChanged(Form root, String id, String value) {
        if (expression.idChanged(id, value)) {
            root.idChanged(this.id, this.expression.toString());
        }
    }

    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException {
        validator.validateRedeclaration(this);
        validator.validateCyclicDependency(this);
        for (String id : expression.getIds()) {
            validator.validateId(id);
            validator.validateIdentifierType(id, getType());
        }
    }

    @Override
    public boolean conformsToType(Value.Type type) {
        return expression.getValue().conformsToType(type);
    }

    @Override
    public boolean isDependentOn(String id) {
        return Arrays.stream(expression.getIds()).anyMatch(i -> i.equals(id));
    }

    @Override
    public Value.Type getType() {
        return expression.getValue().getType();
    }

    @Override
    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    @Override
    public StringProperty getStringValueProperty() {
        return expression.getStringValueProperty();
    }

    @Override
    public void show(FXMLExampleController screen) {
        screen.showComputed(this);
    }

    @Override
    public String toString() {
        return "<Computed>: " + label + " " + id + ": " + expression.getValue().getType() + " = " + expression;
    }
}
