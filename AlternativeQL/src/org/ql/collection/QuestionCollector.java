package org.ql.collection;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollector implements FormVisitor<Void, List<Question>>, StatementVisitor<Void, List<Question>> {

    private QuestionCollector() {
    }

    public static List<Question> collect(Form form) {
        List<Question> questions = new ArrayList<>();

        form.accept(new QuestionCollector(), questions);

        return questions;
    }

    @Override
    public Void visit(Form form, List<Question> questions) {
        visitStatements(questions, form.getStatements());
        return null;
    }

    @Override
    public Void visit(IfThen ifThen, List<Question> questions) {

        visitStatements(questions, ifThen.getThenStatements());

        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse, List<Question> questions) {
        visitStatements(questions, ifThenElse.getThenStatements());
        visitStatements(questions, ifThenElse.getElseStatements());

        return null;
    }

    @Override
    public Void visit(Question question, List<Question> questions) {
        questions.add(question);

        return null;
    }

    private void visitStatements(List<Question> questions, List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this, questions);
        }
    }
}
