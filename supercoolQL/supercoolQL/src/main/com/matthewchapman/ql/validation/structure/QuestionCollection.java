package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.visitor.QLStatementVisitor;

import java.util.*;

/**
 * Created by matt on 03/03/2017.
 * <p>
 * Gathers all of the questions contained within a given Form, allows checking for duplicates
 */
public class QuestionCollection implements QLStatementVisitor<Void, String> {

    private final List<Question> questionList;
    private final HashMap<String, Type> typeTable;
    private final QLErrorLogger logger;

    public QuestionCollection() {
        this.typeTable = new HashMap<>();
        this.questionList = new ArrayList<>();
        this.logger = new QLErrorLogger();
    }

    public QLErrorLogger gatherQuestions(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        findDuplicates();

        return this.logger;
    }

    public Map<String, Type> getTypeTable() {
        return this.typeTable;
    }

    public List<Question> getQuestionList() {
        return this.questionList;
    }

    // hooray for O(n) complexity!
    public boolean findDuplicates() {
        Set<String> questionIDs = new HashSet<>();

        for (Question question : questionList) {
            if (!questionIDs.add(question.getName())) {
                logger.addError(question.getLine(), question.getColumn(), question.getName(), "Question with this ID already defined");
            }
        }

        if (logger.getErrorNumber() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public Void visit(Question question, String context) {
        questionList.add(question);
        typeTable.put(question.getName(), question.getType());
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
        typeTable.put(calculatedQuestion.getName(), calculatedQuestion.getType());
        return null;
    }
}
