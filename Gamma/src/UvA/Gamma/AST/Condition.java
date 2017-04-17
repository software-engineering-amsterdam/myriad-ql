package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.Visitors.Visitor;

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
        return ((BooleanValue) expression.value()).toBoolean();
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        thenBlockItems.forEach(item -> item.idChanged(root, changed, value));
        elseBlockItems.forEach(item -> item.idChanged(root, changed, value));
    }

    @Override
    public void accept(Visitor visitor) {
        thenBlockItems.forEach(formItem -> formItem.accept(visitor));
        elseBlockItems.forEach(formItem -> formItem.accept(visitor));
        expression.accept(visitor);
        visitor.visit(this);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        thenBlockItems.forEach(builder::append);
        elseBlockItems.forEach(builder::append);
        return "<Condition>: (" + ")" + builder;
    }
}
