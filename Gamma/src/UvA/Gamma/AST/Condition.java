package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.BooleanExpression;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Condition implements FormItem {
    private List<FormItem> thenBlockItems;
    /* can be empty if no elseBlock is specified */
    private List<FormItem> elseBlockItems;
    private BooleanExpression expression;
    private GridPane thenBlockPane;
    private GridPane elseBlockPane;

    public Condition(BooleanExpression expression) {
        this.thenBlockItems = new ArrayList<>();
        this.elseBlockItems = new ArrayList<>();
        this.expression = expression;
    }

    public void addThenBlockItem(FormItem item) {
        this.thenBlockItems.add(item);
    }

    public void addElseBlockItem(FormItem item) {
        this.elseBlockItems.add(item);
    }

    public boolean evaluateExpression() {
        assert expression != null;
        expression.evaluate();
        return expression.getValue() != null && expression.getValue().getValue();
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        assert expression != null;
        assert thenBlockPane != null;
        assert elseBlockPane != null;
        expression.idChanged(changed.isDependencyOf(this), value);
        if (evaluateExpression()) {
            thenBlockPane.setVisible(true);
            elseBlockPane.setVisible(false);
        } else {
            thenBlockPane.setVisible(false);
            elseBlockPane.setVisible(true);
        }

        thenBlockItems.forEach(item -> item.idChanged(root, changed, value));
        elseBlockItems.forEach(item -> item.idChanged(root, changed, value));
    }

    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException {
        for (FormItem item : thenBlockItems) {
            item.accept(validator);
        }
        for (FormItem item : elseBlockItems) {
            item.accept(validator);
        }
    }

    @Override
    public Value.Type validateIdentifierType(String identifier, Value.Type type) {
        Optional<FormItem> thenItem = thenBlockItems.stream().filter(formItem -> formItem.validateIdentifierType(identifier, type) != null).findFirst();
        Optional<FormItem> elseItem = elseBlockItems.stream().filter(formItem -> formItem.validateIdentifierType(identifier, type) != null).findFirst();
        if (thenItem.isPresent()) {
            return thenItem.get().validateIdentifierType(identifier, type);
        } else if (elseItem.isPresent()) {
            return elseItem.get().validateIdentifierType(identifier, type);
        } else {
            return null;
        }
    }

    @Override
    public String validateRedeclaration(FormItem item) {
        return null;
    }

    @Override
    public String validateLabel(FormItem item) {
        if (this == item) return null;
        Optional<FormItem> optionalResult = thenBlockItems.stream().filter(formItem -> formItem.validateLabel(item) != null).findFirst();
        Optional<FormItem> optionalElseResult = elseBlockItems.stream().filter(formItem -> formItem.validateLabel(item) != null).findFirst();
        if (optionalResult.isPresent()) {
            return optionalResult.get().validateLabel(item);
        } else if (optionalElseResult.isPresent()) {
            return optionalResult.get().validateLabel(item);
        }
        return null;
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        Optional<FormItem> optionalResult = thenBlockItems.stream().filter(
                formItem -> validateCyclicDependencyForChild(formItem, item)).findFirst();
        Optional<FormItem> optionalElseResult = elseBlockItems.stream().filter(
                formItem -> validateCyclicDependencyForChild(formItem, item)).findFirst();
        if (optionalResult.isPresent()) {
            return optionalResult.get().validateCyclicDependency(item);
        } else if (optionalElseResult.isPresent()) {
            return optionalElseResult.get().validateCyclicDependency(item);
        } else {
            return new Pair<>(null, null);
        }
    }

    private boolean validateCyclicDependencyForChild(FormItem child, FormItem item) {
        return !child.validateRedeclaration(item).isEmpty();
    }

    @Override
    public boolean isDependentOn(String id) {
        return expression.isDependentOn(id) ||
                thenBlockItems.stream().anyMatch(item -> item.isDependentOn(id)) ||
                elseBlockItems.stream().anyMatch(item -> item.isDependentOn(id));
    }

    @Override
    public String isDependencyOf(FormItem item) {
        return null;
    }

    @Override
    public boolean hasId(String id) {
        return false;
    }

    @Override
    public boolean containsId(String id) {
        return thenBlockItems.stream().anyMatch(formItem -> formItem.containsId(id)) ||
                elseBlockItems.stream().anyMatch(formItem -> formItem.containsId(id));
    }

    @Override
    public boolean containsLabel(String label) {
        return thenBlockItems.stream().anyMatch(formItem -> formItem.containsLabel(label)) ||
                elseBlockItems.stream().anyMatch(formItem -> formItem.containsLabel(label));
    }

    @Override
    public void show(FXMLController screen) {
        thenBlockPane = screen.startRenderCondition();
        thenBlockItems.forEach(formItem -> formItem.show(screen));
        screen.stopRenderCondition();

        elseBlockPane = screen.startRenderCondition();
        elseBlockItems.forEach(formItem -> formItem.show(screen));
        screen.stopRenderCondition();

        if (!evaluateExpression()) {
            thenBlockPane.setVisible(false);
        } else {
            elseBlockPane.setVisible(false);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        thenBlockItems.forEach(builder::append);
        elseBlockItems.forEach(builder::append);
        return "<Condition>: (" + expression + ")" + builder;
    }
}
