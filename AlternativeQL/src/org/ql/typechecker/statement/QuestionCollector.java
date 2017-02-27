package org.ql.typechecker.statement;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollector {

    private final StatementVisitor<List<Question>> questionVisitor;

    public QuestionCollector(StatementVisitor<List<Question>> questionVisitor) {
        this.questionVisitor = questionVisitor;
    }

    public List<Question> collect(Form form) {
        List<Question> questions = new ArrayList<>();

        for (Statement statement : form.getStatements()) {
            questions.addAll(statement.accept(questionVisitor));
        }

        return questions;
    }
}
