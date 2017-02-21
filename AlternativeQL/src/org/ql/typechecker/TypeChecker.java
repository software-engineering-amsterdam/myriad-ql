package org.ql.typechecker;


import org.ql.ast.Form;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

public class TypeChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    @Override
    public Void visit(Form form) throws Throwable {
        return null;
    }

    @Override
    public Void visit(IfThen ifThen) throws Throwable {
        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse) throws Throwable {
        return null;
    }

    @Override
    public Void visit(Question question) throws Throwable {
        return null;
    }
}
