package org.ql.gui.elements.visitor;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.GUIEvaluator;
import org.ql.gui.QuestionElementContainer;
import org.ql.gui.elements.QuestionElement;

import java.util.List;

public class VisibleQuestionsVisitor implements FormVisitor<Void, List<QuestionElement>>,
        StatementVisitor<Void, List<QuestionElement>> {

    private final ValueTable valueTable;
    private GUIEvaluator evaluator;
    private QuestionElementContainer elementContainer = new QuestionElementContainer();

    public VisibleQuestionsVisitor(ValueTable valueTable) {
        this.valueTable = valueTable;
        this.evaluator = new GUIEvaluator(this.valueTable);
    }

    @Override
    public Void visitForm(Form form, List<QuestionElement> elements) {

        for (Statement statement : form.getStatements())
            statement.accept(this, null);

        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, List<QuestionElement> elements) {
        if (evaluator.evaluateIfThen(ifThen)) {
            for (Statement statement : ifThen.getThenStatements())
                statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, List<QuestionElement> elements) {
        if (evaluator.evaluateIfThen(ifThenElse)) {
            for (Statement statement : ifThenElse.getThenStatements())
                statement.accept(this, null);
        } else {
            for (Statement statement : ifThenElse.getElseStatements())
                statement.accept(this, null);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, List<QuestionElement> elements) {

        // getting here means we add the question to the stack (A NEW EMPTY STACK)


        // 1. get/create the question element
        // 2. Check if the element has user input
        //  - if yes - ignore the element
        //  - if no - eval and put new value

        QuestionElement questionElement = elementContainer.getQuestionElement(question);

        if (questionElement.isDirty()) {
            //
        } else {
            Value value = evaluator.evaluateExpression(question);
            questionElement.setValue(value);
        }

        valueTable.declare(question.getId(), questionElement.getValue());

        visibleQuestions.add(questionElement);

        return null;
    }

}
