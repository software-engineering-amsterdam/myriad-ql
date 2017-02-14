package org.lemonade;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MakeForm extends QLBaseListener {

    private Stack<ASTNode> stack = new Stack<ASTNode>();
    private Stack<ArrayList<Question>> questions = new Stack<ArrayList<Question>>();

    public ASTNode getForm() {
        return stack.get(0);
    }

    @Override public void enterForm(final QLParser.FormContext ctx) {
        System.err.println("Entering form");
        System.err.println("FORMLABEL:" + ctx.identifier().getText());
        Form form = new Form(ctx.identifier().getText(), 42);//FIXME lineno Hardcoded
        stack.add(form);
    }

    @Override public void exitForm(final QLParser.FormContext ctx) {
        System.err.println("Exiting form");
    }

    @Override public void enterBlock(final QLParser.BlockContext ctx) {
        System.err.println("Entering Block");
        ArrayList<Question> newList = new ArrayList<Question>();
        questions.add(newList);
    }

    @Override public void exitBlock (final QLParser.BlockContext ctx) {
//        System.err.println();
//        ArrayList<Question> child = questions.pop();
//        getForm().addChild(child);
    }

    @Override public void enterQuestion(final QLParser.QuestionContext ctx) {
//        System.err.println("Entering question");
    }

    @Override public void exitQuestion(final QLParser.QuestionContext ctx) {
        System.err.println("Exiting question");
        System.err.println("IDENTIFIER:"+ ctx.identifier().getText());
        System.err.println("LABEL:"+ ctx.label().getText());
        System.err.println("TYPE:"+ ctx.type_specifier().getText());
        Question question = new Question(ctx.identifier().getText(), ctx.label().getText(), null,42);
        questions.peek().add(question);
    }

    @Override public void enterExpr(final QLParser.ExprContext ctx) {
//        System.err.println("Entering expr");
    }

    @Override public void exitExpr(final QLParser.ExprContext ctx) {
//        System.err.println("Exiting expr");
    }

    @Override public void enterConditional(final QLParser.ConditionalContext ctx) {
        System.err.println("Entering conditional");
    }

    @Override public void exitConditional(final QLParser.ConditionalContext ctx) {
//        System.err.println("Exiting conditional");
    }

}
