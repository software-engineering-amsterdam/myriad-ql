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

    public void formAddQuestion( ArrayList<Question> questionList)
    {
        this.questionList = questionList;
    }

    public void formAddIfStatement( ArrayList<IfStatement> IfList)
    {
        this.ifStatementList = IfList;
    }

    public ArrayList<Question> getFormQuestionList() { return questionList; }

    public ArrayList<IfStatement> getFormIfStatementList() { return ifStatementList; }

}
