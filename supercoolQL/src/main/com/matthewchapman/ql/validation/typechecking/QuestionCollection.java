package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by matt on 03/03/2017.
 */
public class QuestionCollection extends AbstractQLVisitor<Void> {

    ArrayList<Question> questionList;
    HashMap<String, Question> questionHashMap;

    public QuestionCollection() {
        questionHashMap = new HashMap<>();
        questionList = new ArrayList<>();
    }

    public void gatherQuestions(Form form) {
        for(Statement statement : form.getStatements())
        {
            statement.accept(this);
        }
    }

    public ArrayList<Question> getQuestionList() {
        return this.questionList;
    }

    public void findDuplicates() {
        Set<String> questionIDs = new HashSet<>();

        for(Question question : questionList) {
            if(!questionIDs.add(question.getName())) {
                System.err.println("");
            }
        }
    }

    @Override
    public Void visit(Question question) {
        questionList.add(question);
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for(Statement statement : ifStatement.getStatements()) {
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
}
