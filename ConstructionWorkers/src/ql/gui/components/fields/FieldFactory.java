/**
 * FieldFactory.java.
 */

package ql.gui.components.fields;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.StringType;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.components.widgets.QLWidget;
import ql.gui.evaluation.Evaluator;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

public class FieldFactory implements FormAndStatementVisitor<Field>, TypeVisitor<Field> {

    private final GUIInterface guiInterface;
    private final Evaluator evaluator;
    private final WidgetFactory widgetFactory;

    private QLWidget qlWidget;
    private SimpleQuestion simpleQuestion;

    public FieldFactory(GUIInterface guiInterface, Context context) {
        this.guiInterface = guiInterface;
        evaluator = new Evaluator(context);
        widgetFactory = new WidgetFactory();
    }

    @Override
    public Field visit(Form form) {
        return null;
    }

    @Override
    public Field visit(SimpleQuestion question) {

        this.qlWidget = widgetFactory.getWidgetForQuestion(question);
        this.simpleQuestion = question;

        return question.getType().accept(this);
    }

    @Override
    public Field visit(ComputedQuestion question) {
        Value result = evaluator.getValueComputedQuestion(question);
        QLWidget widget = this.widgetFactory.getWidgetForQuestion(question);

        widget.setValue(result);
        widget.setReadOnly(true);

        this.qlWidget = widget;
        this.simpleQuestion = question;

        return question.getType().accept(this);
    }

    @Override
    public Field visit(IfStatement statement) {
        return null;
    }

    @Override
    public Field visit(BooleanType type) {
        return new BooleanField(guiInterface, simpleQuestion, qlWidget);
    }

    @Override
    public Field visit(IntegerType type) {
        return new IntegerField(guiInterface, simpleQuestion, qlWidget);
    }

    @Override
    public Field visit(MoneyType type) {
        return new MoneyField(guiInterface, simpleQuestion, qlWidget);
    }

    @Override
    public Field visit(StringType type) {
        return new StringField(guiInterface, simpleQuestion, qlWidget);
    }
}
