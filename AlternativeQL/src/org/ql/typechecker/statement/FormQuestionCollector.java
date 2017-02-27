package org.ql.typechecker.statement;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.collector.QuestionCollector;

import java.util.ArrayList;
import java.util.List;

public class FormQuestionCollector implements QuestionCollector<Form> {

    private final StatementVisitor<List<Question>> questionVisitor;

    public FormQuestionCollector(StatementVisitor<List<Question>> questionVisitor) {
        this.questionVisitor = questionVisitor;
    }

    @Override
    public List<Question> collect(Form form) {
        List<Question> questions = new ArrayList<>();

        for (Statement statement : form.getStatements()) {
            questions.addAll(statement.accept(questionVisitor));
        }

        return questions;
    }
}
