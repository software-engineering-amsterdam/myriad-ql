package com.mcsa.QL;

import java.util.ArrayList;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement implements Node {

    private ArrayList<Question> questionList;
    private ArrayList<IfStatement> ifStatementList;
    public String name;

    public IfStatement(String name) {
        this.name = name;
        this.questionList = new ArrayList<>();
        this.ifStatementList = new ArrayList<>();
    }

    public void ifStatementAddQuestion( ArrayList<Question> quList)
    {
        this.questionList = quList;
    }

    public void ifStatementAddIfStatement( ArrayList<IfStatement> IfList)
    {
        this.ifStatementList = IfList;
    }

    public ArrayList<Question> getQuestionList() { return questionList; }

    public ArrayList<IfStatement> getIfStatementList() { return ifStatementList; }

}
