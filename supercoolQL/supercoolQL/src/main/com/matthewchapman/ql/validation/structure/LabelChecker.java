package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.errorhandling.ErrorLogger;
import com.matthewchapman.ql.validation.type.TypeTable;
import com.matthewchapman.ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by matt on 03/03/2017.
 * <p>
 * Gathers all of the questions contained within a given Form, allows checking for duplicates
 */
public class LabelChecker implements StatementVisitor<Void, String> {

    private final List<Question> questionList;
    private final TypeTable typeTable;
    private final ErrorLogger logger;

    public LabelChecker() {
        this.typeTable = new TypeTable();
        this.questionList = new ArrayList<>();
        this.logger = new ErrorLogger();
    }

    public ErrorLogger gatherQuestions(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        findDuplicates();

        return this.logger;
    }

    public TypeTable getTypeTable() {
        return this.typeTable;
    }

    public List<Question> getQuestionList() {
        return this.questionList;
    }

    public boolean findDuplicates() {
        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        // hooray for O(n) complexity!

        for (Question question : questionList) {
            if (!questionIDs.add(question.getName())) {
                logger.addError(question.getLine(), question.getColumn(), question.getName(), "Question with this ID already defined");
            }

            if(!questionTexts.add(question.getText())) {
                logger.addWarning(question.getLine(), question.getColumn(), question.getName(), "Question with this label already defined");
            }
        }

        return logger.getErrorNumber() > 0;

    }

    @Override
    public Void visit(Question question, String context) {
        questionList.add(question);
        typeTable.addEntry(question.getName(), question.getType());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        for (Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {
        for (Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        for (Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        questionList.add(calculatedQuestion);
        typeTable.addEntry(calculatedQuestion.getName(), calculatedQuestion.getType());
        return null;
    }
}
