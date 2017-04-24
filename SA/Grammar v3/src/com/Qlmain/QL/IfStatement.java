package com.Qlmain.QL;



import com.Qlmain.typesOfExpr.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement extends Statement implements Node {

    private Expression caseStatement;
    private List<Statement> statementsList;
    private int line;

    public IfStatement(int line) {
        this.statementsList = new ArrayList<>();
        this.line=line;
    }

    public void ifStatementAddCase( Expression ifCase )
    {
        this.caseStatement = ifCase;
    }

    public void ifStatementAddStatement( Question qu) { this.statementsList.add(qu); }

    public void ifStatementAddStatement( IfStatement st) { this.statementsList.add(st); }

    public Expression getIfCase() { return caseStatement; }

    public List<Statement> getStatementsList() { return statementsList; }

    public int getIfStatementLine() { return line; }

    @Override
    public boolean isIfStatement() {return true;}

}
