package org.ql.collection.collector;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.collection.Questions;

import java.util.List;

public class FormQuestionCollector implements QuestionCollector<Form> {

    private final StatementVisitor<List<Question>> questionVisitor;

    public FormQuestionCollector(StatementVisitor<List<Question>> questionVisitor) {
        this.questionVisitor = questionVisitor;
    }

    @Override
    public Questions collect(Form node) {
        Questions questions = new Questions();

        for (Statement statement : node.getStatements()) {
            questions.addAll(statement.accept(questionVisitor));
        }

        return questions;
    }
}
