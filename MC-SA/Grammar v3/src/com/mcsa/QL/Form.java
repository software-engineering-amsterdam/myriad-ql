package com.mcsa.QL;

import java.util.ArrayList;

/**
 * Created by matt on 20/02/2017.
 */
public class Form implements Node{

    private ArrayList<Question> questionList;
    private ArrayList<IfStatement> ifStatementList;
    private String formName;

    public Form (String name) {
        this.formName = name;
        this.questionList = new ArrayList<>();
        this.ifStatementList = new ArrayList<>();
    }

    public void replaceQuestions(ArrayList<Question> questionList)
    {
        this.questionList = questionList;
    }

    public void replaceIfStatements(ArrayList<IfStatement> IfList)
    {
        this.ifStatementList = IfList;
    }

    public void addQuestion(Question question){this.questionList.add(question);}

    public void addIfStatement(IfStatement statement){this.ifStatementList.add(statement);}

    public ArrayList<Question> getQuestionList() { return questionList; }

    public ArrayList<IfStatement> getIfStatementList() { return ifStatementList; }

    public String getName(){return formName;}

}
