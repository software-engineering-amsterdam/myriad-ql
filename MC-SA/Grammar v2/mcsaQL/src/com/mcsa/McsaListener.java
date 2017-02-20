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
    public ArrayList<String> questions = new ArrayList<>();


    @Override
    public void enterStart(QLParser.StartContext ctx) {

        ArrayList<TreeNode<String>> questions = new ArrayList<>();

        System.out.println("ID: " + ctx.ID().getText());

        //ctx.statementContent().categorise().forEach(item -> System.out.println("Question: " + item.STRING().getText()));

    }

    @Override
    public void exitStart(QLParser.StartContext ctx) {
        questions.forEach(item -> System.out.println(item));
    }

    @Override
    public void enterStatementContent(QLParser.StatementContentContext ctx) {


//        ctx.categorise().forEach(item -> {
//
//            if(item.getRuleIndex() == 2) questions.add(item.STRING().getText());
//        });

        ctx.categorise().forEach(item -> System.out.println(item.getRuleIndex()));
    }



    @Override
    public void exitStatementContent(QLParser.StatementContentContext ctx) {

    }

    @Override
    public void enterCategorise(QLParser.CategoriseContext ctx) {
        //System.out.println(ctx.getParent().getParent().getChild(0).getText());

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
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
