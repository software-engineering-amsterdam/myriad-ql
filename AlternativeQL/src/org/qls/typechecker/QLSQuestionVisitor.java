package org.qls.typechecker;

import org.ql.ast.type.Type;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.*;
import org.qls.typechecker.issues.errors.DuplicateQLSQuestion;
import org.qls.typechecker.issues.errors.UndefinedQLQuestion;
import org.qls.typechecker.issues.errors.UnsupportedWidgetForQLQuestionType;


public class QLSQuestionVisitor implements WidgetQuestionVisitor<Void, SymbolTable> {
    private final IssuesStorage issuesStorage;
    private final DefinedQLSQuestionSet definedQLSQuestionsSet;

    public QLSQuestionVisitor(IssuesStorage issuesStorage) {
        this.issuesStorage = issuesStorage;
        definedQLSQuestionsSet = new DefinedQLSQuestionSet();
    }

    public void visitStyleSheet(StyleSheet styleSheet, SymbolTable symbolTable) {
        for (Page page : styleSheet.getPages()) {
            visitPage(page, symbolTable);
        }
    }

    public void visitPage(Page page, SymbolTable symbolTable) {
        for (Section section : page.getSections()) {
            visitSection(section, symbolTable);
        }
    }

    public void visitSection(Section section, SymbolTable symbolTable) {
        section.getQuestions().forEach((question) -> question.accept(this, symbolTable));

        section.getSections().forEach((currentSection) -> visitSection(currentSection, symbolTable));
    }

    private void checkWidgetTypeForQLQuestion(CustomWidgetQuestion question, SymbolTable symbolTable) {
        if (!isWidgetTypeSupportedForQuestion(question, symbolTable)) {
            issuesStorage.addError(new UnsupportedWidgetForQLQuestionType(question));
        }
    }

    private boolean isWidgetTypeSupportedForQuestion(CustomWidgetQuestion question, SymbolTable symbolTable) {
        Type questionType = symbolTable.lookup(question.getIdentifier());
        return questionType.isCompatibleWith(question.getWidget());
    }

    private void checkDuplicateQLSQuestions(WidgetQuestion question) {
        if (definedQLSQuestionsSet.isDeclared(question.getIdentifier())) {
            issuesStorage.addError(new DuplicateQLSQuestion(question));
        } else {
            definedQLSQuestionsSet.declare(question.getIdentifier());
        }
    }

    private void checkUndefinedQLQuestions(WidgetQuestion question, SymbolTable symbolTable) {
        if (!symbolTable.isDeclared(question.getIdentifier())) {
            issuesStorage.addError(new UndefinedQLQuestion(question));
        }
    }

    @Override
    public Void visitCustomWidgetQuestion(CustomWidgetQuestion question, SymbolTable symbolTable) {
        checkDuplicateQLSQuestions(question);
        checkUndefinedQLQuestions(question, symbolTable);
        checkWidgetTypeForQLQuestion(question, symbolTable);
        return null;
    }

    @Override
    public Void visitGenericWidgetQuestion(WidgetQuestion question, SymbolTable symbolTable) {
        checkDuplicateQLSQuestions(question);
        checkUndefinedQLQuestions(question, symbolTable);
        return null;
    }
}
