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

import java.util.List;

public class TypeChecker {

    private final Form form;
    private final QuestionStorage questionStorage;

    private final IssuesStorage issuesStorage;

    private final TypeMismatchVisitor typeMismatchVisitor;
    private final CircularDependencyVisitor circularDependenciesVisitor;

    public TypeChecker(Form form, IssuesStorage issuesStorage) {
        this.form = form;
        this.issuesStorage = issuesStorage;

        questionStorage = new QuestionStorage();

        typeMismatchVisitor = new TypeMismatchVisitor(issuesStorage);
        circularDependenciesVisitor = new CircularDependencyVisitor(issuesStorage);
    }

    public void checkForm() {
        checkForTypeMismatches();
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

    private void checkForTypeMismatches() {
        typeMismatchVisitor.visitForm(form, questionStorage.createSymbolTable(form));
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
}
