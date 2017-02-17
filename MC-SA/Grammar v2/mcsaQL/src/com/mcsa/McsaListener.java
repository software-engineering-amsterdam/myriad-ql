package com.mcsa;

import com.mcsa.antlr.QLListener;
import com.mcsa.antlr.QLParser;
import com.mcsa.Ql.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

/**
 * Created by matt on 14/02/2017.
 */
public class McsaListener implements QLListener {

    ArrayList<Question> questionList;
    ArrayList<Form> formList;

    public McsaListener()
    {
        questionList = new ArrayList<>();
    }

    @Override
    public void enterStart(QLParser.StartContext ctx) {

        //gets the name of the form
        System.out.println("Form found. ID: " + ctx.ID().getText());

        //adds all the questions that are children to this form to an ArrayList
        ctx.statementContent().categorise().forEach(categorise -> questionList.add(new Question(categorise.STRING().getText())));

        //just prints all of the question names
        questionList.forEach(item -> System.out.println(item.name));
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
