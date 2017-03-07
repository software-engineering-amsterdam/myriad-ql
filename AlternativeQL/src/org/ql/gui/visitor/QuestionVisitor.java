package org.ql.gui.visitor;


import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

public class QuestionVisitor implements FormVisitor<Void, Void>, StatementVisitor<Void, IfThen> {

    @Override
    public Void visitIfThen(IfThen ifThen, IfThen context) {
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, IfThen context) {
        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this, ifThenElse);
        }

        return null;
    }

    @Override
    public Void visitQuestion(Question question, IfThen context) {


        return null;
    }

    @Override
    public Void visitForm(Form form, Void context) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        return null;
    }
}
