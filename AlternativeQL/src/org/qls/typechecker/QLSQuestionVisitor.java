package org.qls.typechecker;

import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.Page;
import org.qls.ast.page.Question;
import org.qls.ast.page.Section;
import org.qls.typechecker.issues.errors.DuplicateQLSQuestion;
import org.qls.typechecker.issues.errors.UndefinedQLQuestion;
import org.qls.typechecker.issues.errors.UnsupportedWidgetForQLQuestionType;

public class QLSQuestionVisitor {
    private final IssuesStorage issuesStorage;
    private final SymbolTable symbolTable;
    private final DefinedQLSQuestionSet definedQLSQuestionsSet = new DefinedQLSQuestionSet();

    public QLSQuestionVisitor(IssuesStorage issuesStorage, StyleSheet styleSheet, SymbolTable symbolTable) {
        this.issuesStorage = issuesStorage;
        this.symbolTable = symbolTable;

        visitStyleSheet(styleSheet);
    }

    public void visitStyleSheet(StyleSheet styleSheet) {
        for (Page page : styleSheet.getPages()) {
            visitPage(page);
        }
    }

    public void visitPage(Page page) {
        for (Section section : page.getSections()) {
            visitSection(section);
        }
    }

    public void visitSection(Section section) {
        for (Question question : section.getQuestions()) {
            visitQuestion(question);
        }

        for (Section innerSection : section.getSections()) {
            visitSection(innerSection);
        }
    }

    public void visitQuestion(Question question) {
        checkDuplicateQLSQuestions(question);
        checkUndefinedQLQuestions(question);
        checkWidgetTypeForQLQuestion(question);
    }

    private void checkWidgetTypeForQLQuestion(Question question) {
        if (!isWidgetTypeSupportedForQuestion(question)) {
            issuesStorage.addError(new UnsupportedWidgetForQLQuestionType(question));
        }
    }

    private boolean isWidgetTypeSupportedForQuestion(Question question) {
        if (question.getWidget() == null) {
            return true;
        }

        return question.getWidget().getSupportedTypes().contains(symbolTable.lookup(question.getIdentifier()));
    }

    private void checkDuplicateQLSQuestions(Question question) {
        if (definedQLSQuestionsSet.isDeclared(question.getIdentifier())) {
            issuesStorage.addError(new DuplicateQLSQuestion(question));
        } else {
            definedQLSQuestionsSet.declare(question.getIdentifier());
        }
    }

    private void checkUndefinedQLQuestions(Question question) {
        if (!symbolTable.isDeclared(question.getIdentifier())) {
            issuesStorage.addError(new UndefinedQLQuestion(question));
        }
    }
}
