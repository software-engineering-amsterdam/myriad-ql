package com.mcsa.parsing;

import com.mcsa.QL.*;
import com.mcsa.antlr.*;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class mcsaQLVisitor extends AbstractParseTreeVisitor implements QLVisitor {

    @Override
    public Node visitFormDeclaration(QLParser.FormDeclarationContext ctx) {

        //get the name of the form
        String formName = ctx.ID().getText();

        //instantiate structures to contain the questions and if statement roots
        //List<Question> questions = new ArrayList<>();
        //List<IfStatement> ifStatements = new ArrayList<>();

        //new form to pass back
        Form form = new Form(formName);

        //iterate through each "statement" in the form
        for (QLParser.StatementContext statementContext : ctx.statement()) {

            //if we hit a question
            if (statementContext.question() != null) {
                //visit the question to get a Question object back
                Question result = (Question) visit(statementContext);
                form.addStatement(result);
            }
            //if we hit an if statement
            else if (statementContext.ifStatement() != null) {
                //visit the if statement to get an IfStatement object back
                IfStatement result = (IfStatement) visit(statementContext);
                form.addStatement(result);
            }
        }
        for (Statement st : form.getStatementList()) {
            System.out.println(st.getClass() + " " + st.getClass().equals(Question.class));
        }

        //add the structures to the form
        //if (!questions.isEmpty()) {
        //    form.addStatement((Question) questions);
        //}
        //if (!ifStatements.isEmpty()) {
        //    form.addStatement(ifStatements);
        //}

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
    public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {

        //visit the if case
        IfStatement tempIfStatement = new IfStatement();

        Expr ifCaseHandle = (Expr) visit(ctx.expression());
        tempIfStatement.ifStatementAddCase(ifCaseHandle);

        //ArrayList<Question> listOfQuestions = new ArrayList<>();
        //ArrayList<IfStatement> listOfIfStatements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.statement()) {

            //if we hit a question
            if (statementContext.question() != null) {
                //visit the question to get a Question object back
                Question result = (Question) visit(statementContext);
                tempIfStatement.ifStatementAddStatement(result);
            }
            //if we hit an if statement
            else if (statementContext.ifStatement() != null) {
                //visit the if statement to get an IfStatement object back
                IfStatement result = (IfStatement) visit(statementContext);
                tempIfStatement.ifStatementAddStatement(result);
            }
        }

        return tempIfStatement;
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {

        //grab the question metadata and return the object
        String name = ctx.questionParameters().ID().getText();

        Expr type = (Expr) visit(ctx.questionParameters().type());
        String text = ctx.STRING().getText().replace("\"", "");
        //System.out.println(name + " " + type + " " + text);
        return new Question(name, text, type);

    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Expr visitType(QLParser.TypeContext ctx) {
        Expr typeCheck = new Expr();

        if ( ctx.children.size() == 1) {
            //Expr.Value i = typeCheck.new Value(ctx.getText());
            //typeCheck.Value(ctx.getText());
            return typeCheck.new StrValue( ctx.getText());

        }else if(ctx.children.size() > 1){

            return typeCheck.new giveValEqual(ctx.children.get(0).getText(), (Expr) visit(ctx.expression()) );
           // typeCheck.addRight((Expr) visit(ctx.expression()));
        }

        return typeCheck;
    }

    @Override
    public Object visitGreaterSmallerEqqual(QLParser.GreaterSmallerEqqualContext ctx) {
        Expr typeGreaterSmallerEqqual = new Expr();
        if (ctx.children.size() != 1) {

            if (ctx.op.getText().equals("<")) {
                return typeGreaterSmallerEqqual.new Smaller((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else if (ctx.op.getText().equals(">")){
                return typeGreaterSmallerEqqual.new Greater((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else if (ctx.op.getText().equals("<=")){
                return typeGreaterSmallerEqqual.new SmallerEq((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else if (ctx.op.getText().equals(">=")){
                return typeGreaterSmallerEqqual.new GreaterEq((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else if (ctx.op.getText().equals("==")){
                return typeGreaterSmallerEqqual.new Equal((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }

        }
        return typeGreaterSmallerEqqual;
    }

    @Override
    public Expr visitParens(QLParser.ParensContext ctx) {
        //System.out.println("hello");
        if (ctx.children.size() != 1) {
            return (Expr) visit(ctx.expression());
        }
        return (Expr) visit(ctx.expression());
    }

    @Override
    public Expr visitBool(QLParser.BoolContext ctx) {

       /* Expr bool = new Expr();
        if (ctx.getText().equals("true")) {
            return bool.new Bool(true);
        }else {
            return bool.new Bool(false);
        }*/
       return null;
    }

    @Override
    public Expr visitMulDiv(QLParser.MulDivContext ctx) {
        //System.out.println("hellomul");
        Expr typeMulDiv = new Expr();
        if (ctx.children.size() != 1) {
            //System.out.println(ctx.mathaction(1).children.get(0).getText());

            if (ctx.op.getText().equals("*")) {
                return typeMulDiv.new Mul((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else {
                return typeMulDiv.new Div((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }

        }
        return typeMulDiv;
    }

    @Override
    public Expr visitAddSub(QLParser.AddSubContext ctx) {
        Expr typeAddSub = new Expr();
        //System.out.println("helloadd");
        if (ctx.children.size() != 1) {
            if (ctx.op.getText().equals("+")) {
                return typeAddSub.new Add((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else {
                return typeAddSub.new Sub((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }
        }
        return typeAddSub;
    }

    @Override
    public Expr visitId(QLParser.IdContext ctx) {
        //System.out.println("helloid " + ctx.ID().getText());
        Expr typeId = new Expr();
        return typeId.new IdValue(ctx.ID().getText());
        //return typeId;
    }

    @Override
    public Expr visitAndOr(QLParser.AndOrContext ctx) {
        Expr typeAndOr = new Expr();
        if (ctx.children.size() != 1) {
            if (ctx.op.getText().equals("AND")) {
                return typeAndOr.new And((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }else if (ctx.op.getText().equals("OR")) {
                return typeAndOr.new Or((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
            }
        }
        return typeAndOr;
    }

    @Override
    public Object visitInt(QLParser.IntContext ctx) {
        Expr typeInt = new Expr();
        if (ctx.NUMBER().toString().contains(".")) {
            return typeInt.new DoubleValue( Double.parseDouble(ctx.NUMBER().toString()) );
            //typeInt.addToken( Integer.parseInt(ctx.NUMBER().toString()) );
        }else {
            //System.out.println("helloint " + Integer.parseInt(ctx.NUMBER().toString()));
            return typeInt.new IntValue( Integer.parseInt(ctx.NUMBER().toString()) );
        }
        //return typeInt;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}

