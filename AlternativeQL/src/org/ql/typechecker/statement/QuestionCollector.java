package org.ql.typechecker.statement;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollector {

    private final QuestionVisitor questionVisitor = new QuestionVisitor();

    public List<Question> collect(Form form) {
        List<Question> questions = new ArrayList<>();

        for (Statement statement : form.getStatements()) {
            questions.addAll(statement.accept(questionVisitor));
        }

        return questions;
    }

    private class QuestionVisitor implements StatementVisitor<List<Question>> {

        @Override
        public List<Question> visit(IfThen ifThen) {
            List<Question> questions = new ArrayList<>();

            for (Statement statement : ifThen.getThenStatements()) {
                questions.addAll(statement.accept(this));
            }

            return questions;
        }

        @Override
        public List<Question> visit(IfThenElse ifThenElse) {
            List<Question> questions = new ArrayList<>();

            for (Statement statement : ifThenElse.getThenStatements()) {
                questions.addAll(statement.accept(this));
            }

            for (Statement statement : ifThenElse.getElseStatements()) {
                questions.addAll(statement.accept(this));
            }

            return questions;
        }

        @Override
        public List<Question> visit(Question question) {
            List<Question> questions = new ArrayList<>();
            questions.add(question);

            return questions;
        }
    }

}
