package org.lemonade;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;

import java.util.ArrayList;
import java.util.Stack;

public class MakeForm extends QLBaseListener {

    private Stack<ASTNode> stack = new Stack<ASTNode>();
    private Stack<ArrayList<Question>> questions = new Stack<ArrayList<Question>>();

    public ASTNode getForm() {
        return stack.get(0);
    }

    @Override
    public void enterForm(final QLParser.FormContext ctx) {
        System.err.println("Entering form");
        System.err.println("FORMLABEL:" + ctx.identifier().getText());
        Form form = new Form(ctx.identifier().getText(), null);//FIXME lineno Hardcoded
        stack.add(form);
    }

    @Override
    public void exitForm(final QLParser.FormContext ctx) {
        System.err.println("Exiting form");
    }

    @Override
    public void enterBlock(final QLParser.BlockContext ctx) {
        System.err.println("Entering Block");
        ArrayList<Question> newList = new ArrayList<Question>();
        questions.add(newList);
    }

    @Override
    public void exitBlock(final QLParser.BlockContext ctx) {
        System.err.println();
        ArrayList<Question> child = questions.pop();
        //getForm().addChild(child);
    }

    @Override
    public void enterQuestion(final QLParser.QuestionContext ctx) {
//        System.err.println("Entering question");
    }

    @Override
    public void exitQuestion(final QLParser.QuestionContext ctx) {
        System.err.println("Exiting question");
        System.err.println("IDENTIFIER:" + ctx.identifier().getText());
        System.err.println("LABEL:" + ctx.label().getText());
        System.err.println("TYPE:" + ctx.type_specifier().getText());
        Question question = new Question(ctx.identifier().getText(), ctx.label().getText(), null);
        questions.peek().add(question);
    }


}
