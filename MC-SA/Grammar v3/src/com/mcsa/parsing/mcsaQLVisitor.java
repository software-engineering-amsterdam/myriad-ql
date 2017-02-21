package com.mcsa.parsing;

import com.mcsa.QL.*;
import com.mcsa.antlr.*;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;


public class mcsaQLVisitor extends AbstractParseTreeVisitor implements QLVisitor {

    @Override
    public Node visitFormDeclaration(QLParser.FormDeclarationContext ctx) {

        //get the name of the form
        String formName = ctx.ID().getText();

        //instantiate structures to contain the questions and if statement roots
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<IfStatement> ifStatements = new ArrayList<>();

        //new form to pass back
        Form form = new Form(formName);

        //iterate through each "statement" in the form
        for (QLParser.StatementContext statementContext : ctx.statement()) {

            //if we hit a question
            if (statementContext.question() != null) {
                //visit the question to get a Question object back
                Question result = (Question) visit(statementContext);
                questions.add(result);
            }
            //if we hit an if statement
            else if (statementContext.ifStatement() != null) {
                //visit the if statement to get an IfStatement object back
                IfStatement result = (IfStatement) visit(statementContext);
                ifStatements.add(result);
            }
        }

        //add the structures to the form
        form.replaceQuestions(questions);
        form.replaceIfStatements(ifStatements);

        return form;
    }

    @Override
    public Node visitStatement(QLParser.StatementContext ctx) {

        //visit the correct element of the StatementContext
        if (ctx.question() != null) {
            return (Question) visit(ctx.question());
        }
        else if (ctx.ifStatement() != null){
            return (IfStatement) visit(ctx.ifStatement());
        }

        //TODO: handle this return properly, null is not good
        return null;
    }

    @Override
    public Node visitIfStatement(QLParser.IfStatementContext ctx) {

        //visit the if case
        IfCase ifc = (IfCase) visit(ctx.ifCase());
        System.out.println(ifc.getToken());
         do{

            ifc = ifc.getLeft();
             System.out.println(ifc.getToken());
        }while(ifc.getLeft()!=null);
        //TODO make visitIfStatement return an IfStatement object
        return null;
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {

        //grab the question metadata and return the object
        String name = ctx.questionParameters().ID().getText();
        String type = ctx.questionParameters().type().getText();
        String text = ctx.STRING().getText();
        return new Question(name, text, new Type(type));
    }

    @Override
    public IfCase visitIfCase(QLParser.IfCaseContext ctx) {
        //new ifCase to contain the result
        IfCase ifCaseCheck = new IfCase();

        if (ctx.ifCase().size() > 1) {

            if (ctx.TOKEN() != null) {
                ifCaseCheck.addToken(ctx.TOKEN().getText());
                //System.out.println("ddd");
            }

            //IfCase ifCaseCheckRight = new IfCase();
            ifCaseCheck.addRight((IfCase) visit(ctx.ifCase().get(1)));
            //ifCaseCheckRight.addToken(visit(ctx.ifCase().get(1)));
            //IfCase ifCaseCheckLeft = new IfCase();
            ifCaseCheck.addLeft((IfCase) visit(ctx.ifCase().get(0)));
            //visit(ctx.ifCase().get(0));
        } else {
            //IfCase ifCaseChecklast = new IfCase();
            QLParser.IfCaseArgsContext ifCaseArgsContext = ctx.ifCaseArgs();
            ifCaseCheck.addToken((String) visit(ctx.ifCaseArgs()));
        }

        System.out.println();

        return ifCaseCheck;



        //if there is a token, we grab it and know to check for the elements either side

        //TODO make visitIfCase return an IfCase object

    }

    @Override
    public String visitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {
        System.out.println("hiiiiiiiiii");
        //get the ID or number depending on what exists
        if (ctx.ID() != null)
            return ctx.ID().getText();
        else
            return ctx.NUMBER().getText();

    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Object visitType(QLParser.TypeContext ctx) {
        return null;
    }

    @Override
    public Object visitMathaction(QLParser.MathactionContext ctx) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}

