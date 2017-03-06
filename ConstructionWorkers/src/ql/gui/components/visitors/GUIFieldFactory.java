package ql.gui.components.visitors;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.gui.evaluation.Evaluator;
import ql.gui.GUIInterface;
import ql.gui.components.fields.ComputerQuestionField;
import ql.gui.components.fields.Field;
import ql.gui.components.widgets.Widget;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class GUIFieldFactory implements FormAndStatementVisitor<Field>{

    private final GUIInterface updates;
    private final Evaluator evaluator;
    private final WidgetFactory widgetFactory;

    public GUIFieldFactory(
            GUIInterface updates, Context context, WidgetFactory widgetFactory)
    {
        this.updates = updates;
        this.evaluator = new Evaluator(context);
        this.widgetFactory = widgetFactory;
    }

    @Override
    public Field visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Field visit(SimpleQuestion statement) {
        Widget widget = this.widgetFactory.getWidgetForQuestion(statement);
        FieldTypeVisitor typeVisitor =
                new FieldTypeVisitor(this.updates, statement, widget);
        return  statement.getType().accept(typeVisitor);
    }

    @Override
    public Field visit(ComputedQuestion statement) {
        Value result = evaluator.getValueComputedQuestion(statement);
        Widget widget = this.widgetFactory.getWidgetForQuestion(statement, result);
        return new ComputerQuestionField(updates, statement, widget, result);
    }

    @Override
    public Field visit(IfStatement statement) {
        throw new AssertionError();
    }
}
