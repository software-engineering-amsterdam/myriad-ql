package org.ql.gui.widgets;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.mediator.GUIMediator;

import java.util.HashMap;
import java.util.Map;

public class WidgetContainer implements TypeVisitor<Widget, Question> {

    private final GUIMediator mediator;
    private final Map<Identifier, Widget> widgets;

    public WidgetContainer(GUIMediator mediator) {
        this.mediator = mediator;
        widgets = new HashMap<>();
    }

    public Widget retrieveWidget(Question question) {
        Identifier questionId = question.getId();
        if (!widgets.containsKey(questionId)) {
            widgets.put(questionId, createWidget(question));
        }
        return widgets.get(questionId);
    }

    private Widget createWidget(Question question) {
        return question.getType().accept(this, question);
    }

    @Override
    public Widget visitBooleanType(BooleanType booleanType, Question question) {
        return new CheckBoxWidget(mediator, question);
    }

    @Override
    public Widget visitDateType(DateType dateType, Question question) {
        return null;
    }

    @Override
    public DecimalSpinnerWidget visitFloatType(FloatType floatType, Question question) {
        return new DecimalSpinnerWidget(mediator, question);
    }

    @Override
    public IntegerSpinnerWidget visitIntegerType(IntegerType integerType, Question question) {
        return new IntegerSpinnerWidget(mediator, question);
    }

    @Override
    public Widget visitMoneyType(MoneyType moneyType, Question question) {
        return new DecimalSpinnerWidget(mediator, question);
    }

    @Override
    public Widget visitStringType(StringType stringType, Question question) {
        return new TextInputWidget(mediator, question);
    }

    @Override
    public Widget visitUnknownType(UnknownType unknownType, Question question) {
        throw new AssertionError();
    }
}
