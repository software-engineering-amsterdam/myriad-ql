package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.ast.statement.Question;
import org.ql.collection.QuestionStorage;
import org.ql.collection.Questions;
import org.ql.typechecker.issues.*;
import org.ql.typechecker.issues.error.DuplicatedQuestionDeclarations;
import org.ql.typechecker.issues.error.EmptyQuestionLabel;
import org.ql.typechecker.issues.warning.DuplicatedQuestionLabels;
import org.ql.typechecker.visitor.CircularDependencyVisitor;
import org.ql.typechecker.visitor.TypeMismatchVisitor;
import org.ql.typechecker.visitor.UndefinedSymbolVisitor;

import java.util.List;

public class TypeChecker {

    private final Form form;
    private final QuestionStorage questionStorage;

    private final TypeMismatchVisitor typeMismatchVisitor;
    private final UndefinedSymbolVisitor undefinedSymbolVisitor;
    private final CircularDependencyVisitor circularDependenciesVisitor;

    private final IssuesStorage issuesStorage;

    public TypeChecker(Form form) {
        this.form = form;
        questionStorage = new QuestionStorage();

        SymbolTable symbolTable = questionStorage.createSymbolTable(form);
        typeMismatchVisitor = new TypeMismatchVisitor(symbolTable);
        undefinedSymbolVisitor = new UndefinedSymbolVisitor(symbolTable);
        circularDependenciesVisitor = new CircularDependencyVisitor();

        issuesStorage = new IssuesStorage();
    }

    public void checkForm() {
        checkForTypeMismatches();
        checkForUndefinedSymbols();
        checkForEmptyQuestionLabels();
        checkForCircularDependency();
        checkQuestionDeclarationDuplicates();
        checkQuestionLabelDuplicates();
    }

    private void checkForEmptyQuestionLabels() {
        for (Question question : questionStorage.collectQuestions(form)) {
            if (question.getQuestionText().toString().isEmpty()) {
                issuesStorage.addError(new EmptyQuestionLabel(question.getQuestionText()));
            }
        }
    }

    private void checkForCircularDependency() {
        circularDependenciesVisitor.visitForm(form, null);
    }

    private void checkForUndefinedSymbols() {
        undefinedSymbolVisitor.visitForm(form, null);
    }

    private void checkForTypeMismatches() {
        typeMismatchVisitor.visitForm(form, null);
    }

    private void checkQuestionLabelDuplicates() {
        Questions questions = questionStorage.collectQuestions(form);

        for (Question question : questions) {
            if (questions.hasLabelDuplicates(question)) {
                issuesStorage.addWarning(new DuplicatedQuestionLabels(question));
            }
        }
    }

    private void checkQuestionDeclarationDuplicates() {
        Questions questions = questionStorage.collectQuestions(form);

        for (Question question : questions) {
            if (questions.hasDuplicatedDeclarations(question)) {
                issuesStorage.addError(new DuplicatedQuestionDeclarations(question));
            }
        }
    }

    public List<Issue> getErrors() {
        List<Issue> errors = issuesStorage.getErrors();
        errors.addAll(typeMismatchVisitor.getErrors());
        errors.addAll(undefinedSymbolVisitor.getErrors());
        errors.addAll(circularDependenciesVisitor.getErrors());
        return errors;
    }

    public List<Issue> getWarnings() {
        return issuesStorage.getWarnings();
    }

    public boolean hasErrors() {
        return getErrors().size() > 0;
    }
}
