package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Types.BooleanType;
import UvA.Gamma.Visitors.Visitor;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Condition implements FormItem {
    private List<FormItem> thenBlockItems;
    /* can be empty if no elseBlock is specified */
    private List<FormItem> elseBlockItems;
    //    private BooleanExpression expression;
    private Expression expression;
    private GridPane thenPane;
    private GridPane elsePane;

    public Condition(Expression expression) {
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
        boolean value = ((BooleanValue) expression.value()).toBoolean();
        if (thenPane != null) {
            thenPane.setVisible(value);
        }
        if (elsePane != null) {
            elsePane.setVisible(!value);
        }
        return value;
    }

    public void setThenPane(GridPane thenPane) {
        this.thenPane = thenPane;
        evaluateExpression();
    }

    public void setElsePane(GridPane elsePane) {
        this.elsePane = elsePane;
        evaluateExpression();
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        thenBlockItems.forEach(item -> item.idChanged(root, changed, value));
        elseBlockItems.forEach(item -> item.idChanged(root, changed, value));
    }

    public boolean validateType() {
        return this.expression.value().conformsToType(new BooleanType());
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor.shouldTraverseRecursive()) {
            thenBlockItems.forEach(formItem -> formItem.accept(visitor));
            elseBlockItems.forEach(formItem -> formItem.accept(visitor));
        }
        expression.accept(visitor);
        visitor.visit(this);
    }

    public void visitThenChildNodes(Visitor visitor) {
        thenBlockItems.forEach(formItem -> formItem.accept(visitor));
    }

    public void visitElseChildNodes(Visitor visitor) {
        elseBlockItems.forEach(formItem -> formItem.accept(visitor));
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        thenBlockItems.forEach(builder::append);
        elseBlockItems.forEach(builder::append);
        return "<Condition>: (" + expression + ") { " + builder + "}";
    }
}
