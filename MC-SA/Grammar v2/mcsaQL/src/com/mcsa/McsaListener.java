package com.mcsa;


import com.mcsa.antlr.QLListener;
import com.mcsa.antlr.QLParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 14/02/2017.
 */
public class McsaListener implements QLListener {

    private ArrayList<TreeNode<String>> forms = new ArrayList<>();
    private ArrayList<TreeNode> questions = new ArrayList<>();

    @Override
    public void enterStart(QLParser.StartContext ctx) {

        forms.add(new TreeNode<>(ctx.ID().getText()));

    }

    @Override
    public void exitStart(QLParser.StartContext ctx) {
        //categoriseList.forEach(entry -> System.out.println(entry.getText()));
    }

    @Override
    public void enterStatementContent(QLParser.StatementContentContext ctx) {

    }

    @Override
    public void exitStatementContent(QLParser.StatementContentContext ctx) {

    }

    @Override
    public void enterCategorise(QLParser.CategoriseContext ctx) {
        ParserRuleContext startContext = ctx.getParent();
    }

    @Override
    public void exitCategorise(QLParser.CategoriseContext ctx) {

    }

    @Override
    public void enterIfCase(QLParser.IfCaseContext ctx) {

    }

    @Override
    public void exitIfCase(QLParser.IfCaseContext ctx) {

    }

    @Override
    public void enterIfCaseArgs(QLParser.IfCaseArgsContext ctx) {

    }

    @Override
    public void exitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {

    }

    @Override
    public void enterCaseNewInput(QLParser.CaseNewInputContext ctx) {

    }

    @Override
    public void exitCaseNewInput(QLParser.CaseNewInputContext ctx) {

    }

    @Override
    public void enterType(QLParser.TypeContext ctx) {

    }

    @Override
    public void exitType(QLParser.TypeContext ctx) {

    }

    @Override
    public void enterMathaction(QLParser.MathactionContext ctx) {

    }

    @Override
    public void exitMathaction(QLParser.MathactionContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
//        System.out.println(terminalNode);
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
        System.out.println(errorNode.getText());
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
