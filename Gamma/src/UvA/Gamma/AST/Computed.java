package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Validation.IdentifiersFromExpressionVisitor;
import UvA.Gamma.Visitors.Visitor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Computed extends IdentifiableFormItem {
    private Expression expression;
    private StringProperty stringValue;

    public Computed(String label, Identifier id, Type type, Expression expression) {
        this.label = label;
        this.identifier = id;
        this.expression = expression;
        this.type = type;
        this.stringValue = new SimpleStringProperty("");
    }

    public String getLabel() {
        assert label != null;
        return label;
    }

    public Type getType() {
        return type;
    }

    public boolean validateType() {
        return this.expression.value().conformsToType(this.type);
    }

    @Override
    public void accept(Visitor visitor) {
        expression.accept(visitor);
        visitor.visit(this);
    }

    public boolean isDependentOn(String identifier) {
        IdentifiersFromExpressionVisitor visitor = new IdentifiersFromExpressionVisitor();
        expression.accept(visitor);
        return visitor.getIdentifiers().contains(identifier);
    }

    public Value value() {
        return expression.value();
    }

    public void updateValue() {
        this.stringValue.setValue(expression.value().toString());
    }

    public StringProperty getStringValueProperty() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "<Computed> " + this.identifier + " = " + expression;
    }
}
