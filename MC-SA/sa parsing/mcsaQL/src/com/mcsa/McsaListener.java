package com.mcsa;


import com.mcsa.antlr.QLListener;
import com.mcsa.antlr.QLParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 14/02/2017.
 */
public class McsaListener implements QLListener {

    private TreeNode<String> rootAST ;
    private TreeNode<String> currentNode ;

    @Override
    public void enterFormDeclaration(QLParser.FormDeclarationContext ctx) {
        rootAST = new TreeNode<>("-> form");

        TreeNode<String> formName = new TreeNode<>(ctx.ID().getText());
        rootAST.addChild(formName);

        currentNode = rootAST.getChild(0);

    }

    @Override
    public void exitFormDeclaration(QLParser.FormDeclarationContext ctx) {
        //System.out.println("exit " + ctx);
        TreeTest.parseTree(rootAST);
    }

    @Override
    public void enterContent(QLParser.ContentContext ctx) {
       // for (ParseTree ch : ctx.children) {
            //if (ch.toString()=="question")
           // System.out.println("cont " + ch.getText());
       // }
    }

    @Override
    public void exitContent(QLParser.ContentContext ctx) {

    }

    @Override
    public void enterCategories(QLParser.CategoriesContext ctx) {
       // for (ParseTree ch : ctx.children) {
            //if (ch.toString()=="question")
          //  System.out.println("cat " + ch.getText());
        //}
    }

    @Override
    public void exitCategories(QLParser.CategoriesContext ctx) {

    }

    @Override
    public void enterIfStatement(QLParser.IfStatementContext ctx) {
        TreeNode<String> label = new TreeNode<>("-> IfStatement");
        currentNode.addChild(label);

        TreeNode<String> ifCondition = new TreeNode<>(ctx.ifCase().getText());
        label.addChild(ifCondition);

        currentNode = ifCondition;
    }

    @Override
    public void exitIfStatement(QLParser.IfStatementContext ctx) {
        currentNode = currentNode.getParent();
        currentNode = currentNode.getParent();
    }

    @Override
    public void enterQuestion(QLParser.QuestionContext ctx) {

        TreeNode<String> label = new TreeNode<>("-> Question");
        currentNode.addChild(label);
        TreeNode<String> questionID = new TreeNode<>(ctx.STRING().getText());
        label.addChild(questionID);

        QLParser.QuestionParametersContext questionParam = ctx.questionParameters();
        TreeNode<String> questionArgsID = new TreeNode<>(questionParam.ID().getText());
        TreeNode<String> questionArgsType = new TreeNode<>(questionParam.type().getText());
        questionID.addChild(questionArgsID);
        questionID.addChild(questionArgsType);

    }

    @Override
    public void exitQuestion(QLParser.QuestionContext ctx) {

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
    public void enterQuestionParameters(QLParser.QuestionParametersContext ctx) {

    }

    @Override
    public void exitQuestionParameters(QLParser.QuestionParametersContext ctx) {

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
