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
        if (!questions.isEmpty()) {
            form.replaceQuestions(questions);
        }
        if (!ifStatements.isEmpty()) {
            form.replaceIfStatements(ifStatements);
        }

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
        IfStatement tempIfStatement = new IfStatement();

        IfCase ifc = (IfCase) visit(ctx.ifCase());
        tempIfStatement.ifStatementAddCase(ifc);

        ArrayList<Question> listOfQuestions = new ArrayList<>();
        ArrayList<IfStatement> listOfIfStatements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.statement()) {

            //if we hit a question
            if (statementContext.question() != null) {
                //visit the question to get a Question object back
                Question result = (Question) visit(statementContext);
                listOfQuestions.add(result);
            }
            //if we hit an if statement
            else if (statementContext.ifStatement() != null) {
                //visit the if statement to get an IfStatement object back
                IfStatement result = (IfStatement) visit(statementContext);
                listOfIfStatements.add(result);
            }
        }
        /*Check ifcase args for correctness when parsed
        System.out.println(ifc.getToken());
        do{
            ifc = ifc.getLeft();
            System.out.println(ifc.getToken());
        }while(ifc.getLeft()!=null);*/

        //add the structures to the form
        if (!listOfQuestions.isEmpty()) {
            tempIfStatement.ifStatementAddQuestion(listOfQuestions);
        }
        if (!listOfIfStatements.isEmpty()) {
            tempIfStatement.ifStatementAddIfStatement(listOfIfStatements);
        }
        //TODO make visitIfStatement return an IfStatement object
        return tempIfStatement;
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {

        //grab the question metadata and return the object
        String name = ctx.questionParameters().ID().getText();

        //old version
        //String type = ctx.questionParameters().type().getText();
        //new version
        Type type = (Type) visit(ctx.questionParameters().type());
        String text = ctx.STRING().getText();
        //System.out.println(name + " " + type + " " + text);
        return new Question(name, text, type);

    }

    @Override
    public IfCase visitIfCase(QLParser.IfCaseContext ctx) {
        //new ifCase to contain the result
        IfCase ifCaseCheck = new IfCase();

        if (ctx.ifCase().size() > 1) {

            if (ctx.TOKEN() != null) {
                ifCaseCheck.addToken(ctx.TOKEN().getText());
            }

            ifCaseCheck.addRight((IfCase) visit(ctx.ifCase().get(1)));
            ifCaseCheck.addLeft((IfCase) visit(ctx.ifCase().get(0)));

        } else {
            ifCaseCheck.addToken((String) visit(ctx.ifCaseArgs()));
        }

        return ifCaseCheck;



        //if there is a token, we grab it and know to check for the elements either side

        //TODO make visitIfCase return an IfCase object

    }

    @Override
    public String visitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {
        //System.out.println("hiiiiiiiiii");
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
    public Type visitType(QLParser.TypeContext ctx) {
        Type typeCheck = new Type();

        if ( ctx.children.size() == 1) {

            typeCheck.addToken(ctx.getText());
            return typeCheck;

        }else {

            typeCheck.addToken(ctx.children.get(1).getText());

            Type leftTypeCheck = new Type();
            leftTypeCheck.addToken(ctx.children.get(0).getText());
            typeCheck.addLeft(leftTypeCheck);

            typeCheck.addRight((Type) visit(ctx.mathaction()));
        }
        //printTypeCheck(typeCheck);
        //System.out.println("math" + ctx.mathaction().size());
        return typeCheck;
    }

    @Override
    public Type visitParens(QLParser.ParensContext ctx) {
        //System.out.println("hello");
        if (ctx.children.size() != 1) {
            return (Type) visit(ctx.mathaction());
        }
        return null;
    }

    @Override
    public Type visitMulDiv(QLParser.MulDivContext ctx) {
        //System.out.println("hellomul");
        Type typeMulDiv = new Type();
        if (ctx.children.size() != 1) {
            //System.out.println(ctx.mathaction(1).children.get(0).getText());
            if (ctx.mathaction(1).children.get(0).getText().equals("(")) {
                //System.out.println("eeeeeeeeeee");
                typeMulDiv.addLeft((Type) visit(ctx.mathaction(1)));
                typeMulDiv.addRight((Type) visit(ctx.mathaction(0)));
                typeMulDiv.addToken(ctx.op.getText());
            } else {
                typeMulDiv.addLeft((Type) visit(ctx.mathaction(0)));
                typeMulDiv.addRight((Type) visit(ctx.mathaction(1)));
                typeMulDiv.addToken(ctx.op.getText());
            }
        }
        return typeMulDiv;
    }

    @Override
    public Type visitAddSub(QLParser.AddSubContext ctx) {
        Type typeAddSub = new Type();
        //System.out.println("helloadd");
        if (ctx.children.size() != 1) {
            typeAddSub.addLeft( (Type) visit(ctx.mathaction(0)) );
            typeAddSub.addRight( (Type)visit(ctx.mathaction(1)) );
            typeAddSub.addToken(ctx.op.getText());
        }
        return typeAddSub;
    }

    @Override
    public Type visitId(QLParser.IdContext ctx) {
        //System.out.println("helloid " + ctx.ID().getText());
        Type typeId = new Type();
        typeId.addToken(ctx.ID().getText());
        return typeId;
    }

    @Override
    public Object visitInt(QLParser.IntContext ctx) {
        Type typeInt = new Type();
        if (ctx.NUMBER().toString().contains(".")) {
            //System.out.println("helloint " + Double.parseDouble(ctx.NUMBER().toString()));
            typeInt.addToken( Double.parseDouble(ctx.NUMBER().toString()) );
        }else {
            //System.out.println("helloint " + Integer.parseInt(ctx.NUMBER().toString()));
            typeInt.addToken( Integer.parseInt(ctx.NUMBER().toString()) );
        }
        return typeInt;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}

