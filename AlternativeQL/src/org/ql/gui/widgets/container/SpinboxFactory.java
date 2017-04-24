package org.ql.gui.widgets.container;

import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.DecimalSpinnerWidget;
import org.ql.gui.widgets.IntegerSpinnerWidget;
import org.ql.gui.widgets.SpinnerWidget;

public class SpinboxFactory implements TypeVisitor<SpinnerWidget, Question> {
    private final ValueReviser valueReviser;

    public SpinboxFactory(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
    }

    @Override
    public SpinnerWidget visitBooleanType(BooleanType booleanType, Question question) {
        throw new WrongTypeForSpinner();
    }

    @Override
    public SpinnerWidget visitDateType(DateType dateType, Question question) {
        throw new WrongTypeForSpinner();
    }

    @Override
    public SpinnerWidget visitFloatType(FloatType floatType, Question question) {
        return new DecimalSpinnerWidget(valueReviser, question);
    }

    @Override
    public SpinnerWidget visitIntegerType(IntegerType integerType, Question question) {
        return new IntegerSpinnerWidget(valueReviser, question);
    }

    @Override
    public SpinnerWidget visitMoneyType(MoneyType moneyType, Question question) {
        return new DecimalSpinnerWidget(valueReviser, question);
    }

    @Override
    public SpinnerWidget visitStringType(StringType stringType, Question question) {
        throw new WrongTypeForSpinner();
    }

    @Override
    public SpinnerWidget visitUnknownType(UnknownType unknownType, Question question) {
        throw new WrongTypeForSpinner();
    }

    private class WrongTypeForSpinner extends RuntimeException {
    }
}
