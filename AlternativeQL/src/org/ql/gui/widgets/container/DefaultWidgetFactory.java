package org.ql.gui.widgets.container;

import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.*;

public class DefaultWidgetFactory implements TypeVisitor<GUIWidget, Question> {
    private final ValueReviser valueReviser;

    public DefaultWidgetFactory(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
    }

    public GUIWidget createWidget(Question question) {
        return question.getType().accept(this, question);
    }

    @Override
    public GUIWidget visitBooleanType(BooleanType booleanType, Question question) {
        return new CheckboxWidget(valueReviser, question);
    }

    @Override
    public GUIWidget visitDateType(DateType dateType, Question question) {
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
    public GUIWidget visitStringType(StringType stringType, Question question) {
        return new TextInputWidget(valueReviser, question);
    }

    @Override
    public GUIWidget visitUnknownType(UnknownType unknownType, Question question) {
        throw new AssertionError();
    }
}
