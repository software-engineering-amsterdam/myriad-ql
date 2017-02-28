package org.ql.collection.collector;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionVisitor implements FormVisitor<List<Question>>, StatementVisitor<List<Question>> {

    public static List<Question> collect(Form form) {
        return form.accept(new QuestionVisitor());
    }

    private QuestionVisitor() {

    }

    @Override
    public List<Question> visit(Form form) {
        for (Statement s: form.getStatements()) {
            s.accept(this);
        }
        return null;
    }

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
