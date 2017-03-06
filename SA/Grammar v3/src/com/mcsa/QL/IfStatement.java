package com.mcsa.QL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement extends Statement implements Node {

    private Expr caseStatement;
    private List<Statement> statementsList;

    public IfStatement() {
        this.statementsList = new ArrayList<>();
    }

    public void ifStatementAddCase( Expr ifcase)
    {
        this.caseStatement = ifcase;
    }

    public void ifStatementAddStatement( Question qu) { this.statementsList.add(qu); }

    public void ifStatementAddStatement( IfStatement st) { this.statementsList.add(st); }

    public Expr getIfCase() { return caseStatement; }

    public List<Statement> getStatementsList() { return statementsList; }

    //public List<IfStatement> getIfStatementList() { return ifStatementList; }

}
