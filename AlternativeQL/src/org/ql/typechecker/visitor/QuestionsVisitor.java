package org.ql.typechecker.visitor;

import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.*;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.statement.question.QuestionLabelVisitor;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.issues.error.DuplicatedQuestionDeclarations;
import org.ql.typechecker.issues.error.EmptyQuestionLabel;
import org.ql.typechecker.issues.warning.DuplicatedQuestionLabels;

import java.util.HashSet;
import java.util.Set;

public class QuestionsVisitor implements FormVisitor<Void, SymbolTable>,
        StatementVisitor<Void, SymbolTable>, QuestionLabelVisitor<Void, Question> {
    private final IssuesStorage issuesStorage;
    private final Set<QuestionLabel> existingLabels;

    public QuestionsVisitor(IssuesStorage issuesStorage) {
        this.issuesStorage = issuesStorage;
        existingLabels = new HashSet<>();
    }

    @Override
    public Void visitForm(Form form, SymbolTable symbolTable) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, SymbolTable symbolTable) {
        for (Statement statement : ifThen.getThenStatements()) {
            statement.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, SymbolTable symbolTable) {
        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this, symbolTable);
        }
        for (Statement statement : ifThenElse.getElseStatements()) {
            statement.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, SymbolTable symbolTable) {
        if (symbolTable.isDeclared(question.getId())) {
            issuesStorage.addError(new DuplicatedQuestionDeclarations(question));
        } else {
            symbolTable.declare(question.getId(), question.getType());
        }

        question.getLabel().accept(this, question);

        return null;
    }

    @Override
    public Void visitComputableQuestion(ComputableQuestion question, SymbolTable symbolTable) {
        visitQuestion(question, symbolTable);

        return null;
    }

    @Override
    public Void visitQuestionLabel(QuestionLabel questionLabel, Question question) {
        if (questionLabel.isEmpty()) {
            issuesStorage.addError(new EmptyQuestionLabel(questionLabel));
        }

        if (existingLabels.contains(questionLabel)) {
            issuesStorage.addWarning(new DuplicatedQuestionLabels(question));
        } else {
            existingLabels.add(questionLabel);
        }

        return null;
    }
}
