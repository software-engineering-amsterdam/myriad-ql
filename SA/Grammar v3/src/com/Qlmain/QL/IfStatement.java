package com.Qlmain.QL;



import com.Qlmain.types_Of_Expr.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 21/02/2017.
 */

public class IfStatement implements Statement<IfStatement>, Node {

    private Expression caseStatement;
    private List<Statement> statementsList;
    private int line;

    public IfStatement(int line) {
        this.statementsList = new ArrayList<>();
        this.line=line;
    }

    public void ifStatementAddCase( Expression ifcase)
    {
        this.caseStatement = ifcase;
    }

    public void ifStatementAddStatement( Question qu) { this.statementsList.add(qu); }

    public void ifStatementAddStatement( IfStatement st) { this.statementsList.add(st); }

    public Expression getIfCase() { return caseStatement; }

    public List<Statement> getStatementsList() { return statementsList; }

    public int getIfStatementLine() { return line; }

    @Override
    public void visitst(IfStatement st) {
        for (Statement state: st.statementsList){
            state.visitst(state);
        }
    }
}
