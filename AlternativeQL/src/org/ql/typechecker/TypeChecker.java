package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.typechecker.expression.HashMapSymbolTable;
import org.ql.typechecker.expression.SymbolTable;
import org.ql.typechecker.statement.QuestionCollector;

public class TypeChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private final QuestionCollector questionCollector;

    public TypeChecker(QuestionCollector questionCollector) {
        this.questionCollector = questionCollector;
    }

    @Override
    public Void visit(Form form) {
        new HashMapSymbolTable(questionCollector.collect(form));

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
