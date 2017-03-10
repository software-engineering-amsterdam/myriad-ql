package org.ql.gui.elements.visitor;

import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.elements.*;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.NumericWidget;
import org.ql.gui.widgets.TextWidget;

public class QuestionElementFactory implements TypeVisitor<QuestionElement, Question> {

    private final GUIMediator mediator;

    public QuestionElementFactory(GUIMediator mediator) {
        this.mediator = mediator;
    }

    public QuestionElement createQuestionElement(Question question) {
        return question.getType().accept(this, question);
    }

    @Override
    public BooleanQuestionElement visitBooleanType(BooleanType booleanType, Question question) {
        return new BooleanQuestionElement(mediator, question.getId(), new CheckBoxWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public QuestionElement visitDateType(DateType dateType, Question question) {
        return null;
    }

    @Override
    public DecimalQuestionElement visitFloatType(FloatType floatType, Question question) {
        return new DecimalQuestionElement(mediator, question.getId(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public IntegerQuestionElement visitIntegerType(IntegerType integerType, Question question) {
        return new IntegerQuestionElement(mediator, question.getId(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public DecimalQuestionElement visitMoneyType(MoneyType moneyType, Question question) {
        return new DecimalQuestionElement(mediator, question.getId(), new NumericWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public TextQuestionElement visitStringType(StringType stringType, Question question) {
        return new TextQuestionElement(mediator, question.getId(), new TextWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public QuestionElement visitUnknownType(UnknownType unknownType, Question question) {
        return null;
    }
}
