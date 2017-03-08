package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

import java.util.*;

/**
 * Created by matt on 03/03/2017.
 *
 * Gathers all of the questions contained within a given Form, allows checking for duplicates
 */
public class QuestionCollection extends AbstractQLVisitor<Void> {

    private List<Question> questionList;
    private HashMap<String, Type> typeTable;

    public QuestionCollection() {
        typeTable = new HashMap<>();
        questionList = new ArrayList<>();
    }

    public void gatherQuestions(Form form) {
        for(Statement statement : form.getStatements()) {
            statement.accept(this);
        }
    }

    public Map<String, Type> getTypeTable() {
        return this.typeTable;
    }

    public List<Question> getQuestionList() {
        return this.questionList;
    }

    // hooray for O(n) complexity!
    void findDuplicates() {
        Set<String> questionIDs = new HashSet<>();

        for(Question question : questionList) {
            if(!questionIDs.add(question.getName())) {
                System.err.println("Error: Duplicate Question found");
            }
        }
    }

    @Override
    public Void visit(Question question) {
        questionList.add(question);
        typeTable.put(question.getName(), question.getType());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for(Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for(Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this);
        }

        for(Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion) {
        questionList.add(calculatedQuestion);
        typeTable.put(calculatedQuestion.getName(), calculatedQuestion.getType());
        return null;
    }
}
