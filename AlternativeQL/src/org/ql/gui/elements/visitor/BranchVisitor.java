package org.ql.gui.elements.visitor;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.gui.QuestionElementContainer;
import org.ql.gui.elements.QuestionElement;

import java.util.ArrayList;
import java.util.List;

public class BranchVisitor implements FormVisitor<List<QuestionElement>, ValueTable>,
        StatementVisitor<Void, ValueTable> {

    private final Evaluator evaluator;
    private final QuestionElementContainer elementContainer;

    private List<QuestionElement> elements;

    public BranchVisitor(QuestionElementContainer elementContainer) {
        this.elementContainer = elementContainer;
        evaluator = new Evaluator();
    }

    @Override
    public List<QuestionElement> visitForm(Form form, ValueTable valueTable) {
        elements = new ArrayList<>();

        for (Statement statement : form.getStatements())
            statement.accept(this, valueTable);

        return elements;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, ValueTable valueTable) {
        if (evaluateIfCondition(ifThen.getCondition(), valueTable)) {
            for (Statement statement : ifThen.getThenStatements())
                statement.accept(this, valueTable);
        }

        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, ValueTable valueTable) {
        if (evaluateIfCondition(ifThenElse.getCondition(), valueTable)) {
            for (Statement statement : ifThenElse.getThenStatements())
                statement.accept(this, valueTable);
        } else {
            for (Statement statement : ifThenElse.getElseStatements())
                statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, ValueTable valueTable) {
        elements.add(elementContainer.getQuestionElement(question));
        return null;
    }

    public boolean evaluateIfCondition(Expression expression, ValueTable valueTable) {
        if (evaluator.evaluate(expression, valueTable) instanceof UnknownValue) {
            return false;
        }

        BooleanValue value = ((BooleanValue) evaluator.evaluate(expression, valueTable));

        if(value == null) {
            return false;
        }

        return value.getPlainValue();
    }

}
