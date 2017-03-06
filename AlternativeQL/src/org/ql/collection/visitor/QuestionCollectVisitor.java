package org.ql.collection.visitor;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.collection.Questions;

import java.util.List;

public class QuestionCollectVisitor implements FormVisitor<Void, List<Question>>, StatementVisitor<Void, List<Question>> {

    public Questions collect(Form form) {
        Questions questions = new Questions();

        form.accept(new QuestionCollectVisitor(), questions);

        return questions;
    }

    @Override
    public Void visitForm(Form form, List<Question> questions) {
        visitStatements(questions, form.getStatements());
        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, List<Question> questions) {

        visitStatements(questions, ifThen.getThenStatements());

        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, List<Question> questions) {
        visitStatements(questions, ifThenElse.getThenStatements());
        visitStatements(questions, ifThenElse.getElseStatements());

        return null;
    }

    @Override
    public Void visitQuestion(Question question, List<Question> questions) {
        questions.add(question);

        return null;
    }

    private void visitStatements(List<Question> questions, List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this, questions);
        }
    }
}
