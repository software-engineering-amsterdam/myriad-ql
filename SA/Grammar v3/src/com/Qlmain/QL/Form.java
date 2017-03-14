package com.Qlmain.QL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 20/02/2017.
 */
public class Form implements Node{

    private List<Statement> statementsList;
    private String formName;

    public Form (String name) {
        this.formName = name;
        this.statementsList = new ArrayList<>();
    }

    public void addStatement(Question question) {
        this.statementsList.add(question);
    }

    public void addStatement(IfStatement ifExpression) {
        this.statementsList.add(ifExpression);
    }

    public List<Statement> getStatementList() { return statementsList; }

    public String getName(){return formName;}

}
