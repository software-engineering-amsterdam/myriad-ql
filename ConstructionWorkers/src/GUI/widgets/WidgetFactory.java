package GUI.widgets;

import ASTnodes.statements.SimpleQuestion;
import ASTnodes.types.*;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.Value;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class WidgetFactory implements TypeVisitor<Widget> {

    private String questionLabel;

    public Widget getWidgetForQuestion(SimpleQuestion question) {
        Type type = question.getType();
        this.questionLabel = question.getText();
        return type.accept(this);
    }

    public Widget getWidgetForQuestion(SimpleQuestion question, Value value) {
        Type type = question.getType();
        this.questionLabel = question.getText();

        Widget widget = type.accept(this);
        widget.setValue(value);
        widget.setReadOnly(true);
        return widget;
    }

    public Widget visit(BooleanType type) {
        return new Checkbox(this.questionLabel);
    }

    public Widget visit(IntegerType type) {
        return new IntegerTextField(this.questionLabel);
    }

    public Widget visit(MoneyType type) {
        return new MoneyTextField(this.questionLabel);
    }

    public Widget visit(StringType type) {
        return new TextField(this.questionLabel);
    }

    public Widget visit(UndefinedType undefinedType) {
        throw new AssertionError();
    }
}
