package org.ql.typechecker.statement;

import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.typechecker.ITypeChecker;
import org.ql.typechecker.messages.MessageBag;

public class StatementProxyVisitor implements StatementVisitor<MessageBag> {

    private final ITypeChecker typeChecker;

    public StatementProxyVisitor(ITypeChecker typeChecker) {
        this.typeChecker = typeChecker;
    }

    @Override
    public MessageBag visit(IfThen ifThen) {
        return typeChecker.checkIfThen(ifThen);
    }

    @Override
    public MessageBag visit(IfThenElse ifThenElse) {
        return typeChecker.checkIfThenElse(ifThenElse);
    }

    @Override
    public MessageBag visit(Question question) {
        return typeChecker.checkQuestion(question);
    }
}
