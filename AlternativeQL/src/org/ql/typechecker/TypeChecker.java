package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.collector.QuestionCollector;

public class TypeChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private final QuestionCollector<Form> questionCollector;

    public TypeChecker(QuestionCollector<Form> questionCollector) {
        this.questionCollector = questionCollector;
    }

    @Override
    public Void visit(Form form) {

        return null;
    }

    @Override
    public Void visit(IfThen ifThen) {
        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse) {
        return null;
    }

    @Override
    public Void visit(Question question) {
        return null;
    }
}
