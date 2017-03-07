package org.ql.gui.elements.visitor;

import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.evaluator.value.UnknownValue;
import org.ql.gui.elements.BooleanQuestionElement;
import org.ql.gui.elements.NumericQuestionElement;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.TextQuestionElement;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.NumericWidget;
import org.ql.gui.widgets.TextWidget;

public class QuestionElementBuilder implements TypeVisitor<QuestionElement, Question> {
    @Override
    public BooleanQuestionElement visitBooleanType(BooleanType booleanType, Question question) {
        return new BooleanQuestionElement(question, new UnknownValue(), new CheckBoxWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public QuestionElement visitDateType(DateType dateType, Question question) {
        return null;
    }

    @Override
    public NumericQuestionElement visitFloatType(FloatType floatType, Question question) {
        return new NumericQuestionElement(question, new UnknownValue(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public NumericQuestionElement visitIntegerType(IntegerType integerType, Question question) {
        return new NumericQuestionElement(question, new UnknownValue(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public NumericQuestionElement visitMoneyType(MoneyType moneyType, Question question) {
        return new NumericQuestionElement(question, new UnknownValue(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public TextQuestionElement visitStringType(StringType stringType, Question question) {
        return new TextQuestionElement(question, new UnknownValue(), new TextWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public QuestionElement visitUnknownType(UnknownType unknownType, Question question) {
        return null;
    }
}
