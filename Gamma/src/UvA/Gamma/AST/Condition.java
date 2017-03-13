package UvA.Gamma.AST;

import UvA.Gamma.AST.Expressions.BooleanExpression;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.Validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Condition implements FormItem {
    private List<FormItem> formItems;
    /* can be empty if no elseBlock is specified */
    private List<FormItem> elseBlockItems;
    private BooleanExpression expression;
    private FXMLExampleController screen;

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
    public void idChanged(Form root, FormItem changed, String value) {
        expression.idChanged(changed.isDependencyOf(this), value);
        screen.showCondition(this);
        formItems.forEach(item -> item.idChanged(root, changed, value));
        elseBlockItems.forEach(item -> item.idChanged(root, changed, value));
    }

    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException {
        for (FormItem item : formItems) {
            item.accept(validator);
        }
        for (FormItem item : elseBlockItems) {
            item.accept(validator);
        }
    }

    @Override
    public boolean validateIdentifierType(String identifier, Value.Type type) {
        return formItems.stream().anyMatch(formItem -> formItem.validateIdentifierType(identifier, type));
    }

    @Override
    public String validateRedeclaration(FormItem item) {
        return null;
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        Optional<FormItem> optionalResult = formItems.stream().filter(formItem -> validateCyclicDependencyForChild(formItem, item)).findFirst();
        Optional<FormItem> optionalElseResult = elseBlockItems.stream().filter(formItem -> validateCyclicDependencyForChild(formItem, item)).findFirst();
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
        return formItems.stream().anyMatch(item -> item.isDependentOn(id)) &&
                elseBlockItems.stream().anyMatch(item -> item.isDependentOn(id));
    }

    @Override
    public String isDependencyOf(FormItem item) {
        return null;
    }

    @Override
    public boolean hasId(String id) {
        return false;
//        return childHasId(formItems, id) || childHasId(elseBlockItems, id);
    }

//    private boolean childHasId(List<FormItem> items, String id) {
//        boolean hasId = false;
//        for (FormItem item : items) {
//            hasId = hasId || item.hasId(id);
//        }
//        return hasId;
//    }

    @Override
    public void show(FXMLExampleController screen) {
        this.screen = screen;
        screen.showCondition(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        formItems.forEach(builder::append);
        elseBlockItems.forEach(builder::append);
        return "<Condition>: (" + expression + ")" + builder;
    }

    @Override
    public Value.Type getType() {
        return Value.Type.CONDITION;
    }
}
