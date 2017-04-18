package org.ql.gui.widgets;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.ValueReviser;

import java.util.HashMap;
import java.util.Map;

public class WidgetContainer implements TypeVisitor<Widget, Question> {

    private final ValueReviser valueReviser;
    private final Map<Identifier, Widget> widgets;

    public WidgetContainer(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
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
        return new CheckboxWidget(valueReviser, question);
    }

    @Override
    public Widget visitDateType(DateType dateType, Question question) {
        return null;
    }

    @Override
    public DecimalSpinnerWidget visitFloatType(FloatType floatType, Question question) {
        return new DecimalSpinnerWidget(valueReviser, question);
    }

    @Override
    public IntegerSpinnerWidget visitIntegerType(IntegerType integerType, Question question) {
        return new IntegerSpinnerWidget(valueReviser, question);
    }

    @Override
    public DecimalSpinnerWidget visitMoneyType(MoneyType moneyType, Question question) {
        return new DecimalSpinnerWidget(valueReviser, question);
    }

    @Override
    public Widget visitStringType(StringType stringType, Question question) {
        return new TextInputWidget(valueReviser, question);
    }

    @Override
    public Widget visitUnknownType(UnknownType unknownType, Question question) {
        throw new AssertionError();
    }
}
