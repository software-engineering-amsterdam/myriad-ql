package org.ql.gui.elements.visitor;

import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.ql.gui.elements.*;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.IntegerInputWidget;
import org.ql.gui.widgets.TextInputWidget;

public class QuestionElementFactory implements TypeVisitor<Element, Question> {

    private final GUIMediator mediator;

    public QuestionElementFactory(GUIMediator mediator) {
        this.mediator = mediator;
    }

    public Element createQuestionElement(Question question) {
        return question.getType().accept(this, question);
    }

    @Override
    public Element visitBooleanType(BooleanType booleanType, Question question) {
        return new BooleanElement(mediator, question.getId(), new CheckBoxWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public Element visitDateType(DateType dateType, Question question) {
        return null;
    }

    @Override
    public Element visitFloatType(FloatType floatType, Question question) {
        return null;
    }

    @Override
    public Element visitIntegerType(IntegerType integerType, Question question) {
        return new IntegerElement(mediator, question.getId(), new IntegerInputWidget(question.getQuestionLabel().toString()));
    }

    @Override
    public Element visitMoneyType(MoneyType moneyType, Question question) {
        return null;
    }

    @Override
    public Element visitStringType(StringType stringType, Question question) {
        return new StringElement(mediator, question.getId(), new TextInputWidget(question.getQuestionLabel().toString()));
    }

        @Override
    public Element visitUnknownType(UnknownType unknownType, Question question) {
        return null;
    }
}
