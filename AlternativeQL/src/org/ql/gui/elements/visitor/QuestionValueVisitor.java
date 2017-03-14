package org.ql.gui.elements.visitor;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.elements.ElementContainer;
import org.ql.gui.elements.Element;

public class QuestionValueVisitor implements FormVisitor<Void, ValueTable>, StatementVisitor<Void, ValueTable> {
    private final ElementContainer questionElementContainer;
    private final Evaluator evaluator;

    public QuestionValueVisitor(ElementContainer questionElementContainer) {
        this.questionElementContainer = questionElementContainer;
        evaluator = new Evaluator();
    }

    public void updateValues(Form form, ValueTable valueTable) {
        while (true) {
            ValueTable oldValueTable = valueTable.copy();
            this.visitForm(form, valueTable);
            if (oldValueTable.equals(valueTable)) {
                break;
            }
        }
    }

    @Override
    public Void visitForm(Form form, ValueTable valueTable) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, ValueTable valueTable) {
        for (Statement statement : ifThen.getThenStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, ValueTable valueTable) {
        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this, valueTable);
        }
        for (Statement statement : ifThenElse.getElseStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, ValueTable valueTable) {
        Element questionElement = questionElementContainer.getQuestionElement(question);

        if (!questionElement.isDirty()) {
            Value value;
            if (question.getValue() != null) {
                value = evaluator.evaluate(question.getValue(), valueTable);
            } else {
                value = new UnknownValue();
            }
            valueTable.declare(question.getId(), value);
            questionElement.updateValue(value);
        }

        return null;
    }
}
