package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.Expression;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.Validation.*;
import javafx.beans.property.StringProperty;

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
    public void idChanged(Form root, FormItem changed, String value) {
        if (expression.idChanged(changed.isDependencyOf(this), value)) {
            root.idChanged(this, this.expression.toString());
        }
    }

    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException {
        validator.validateRedeclaration(this);
        validator.validateCyclicDependency(this);
        expression.accept(validator);
    }

    @Override
    public boolean validateIdentifierType(String identifier, Value.Type type) {
        return id.equals(identifier) && !expression.getValue().conformsToType(type);
    }

    @Override
    public String validateRedeclaration(FormItem item) {
        return item != this && item.hasId(this.id) ? this.id : null;
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        return new Pair<>(item.isDependentOn(this.id) ? this.id : null, item.isDependencyOf(this));
    }

    @Override
    public boolean isDependentOn(String id) {
        return expression.isDependentOn(id);
    }

    @Override
    public String isDependencyOf(FormItem item) {
        return item.isDependentOn(this.id) ? this.id : null;
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
    public boolean containsId(String id) {
        return hasId(id);
    }

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
