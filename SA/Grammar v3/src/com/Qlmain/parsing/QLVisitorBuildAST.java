package com.Qlmain.parsing;

import com.Qlmain.QL.*;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions.*;
import com.Qlmain.typesOfExpr.number_ops.numericalExpressions.*;
import com.Qlmain.typesOfExpr.strings.IdValue;
import com.Qlmain.typesOfExpr.strings.SimpleTypeValue;
import com.Qlmain.typesOfExpr.types.*;
import com.Qlmain.antlr.*;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;


public class QLVisitorBuildAST extends AbstractParseTreeVisitor implements QLVisitor {

    @Override
    public Node visitFormDeclaration(QLParser.FormDeclarationContext ctx) {

        //get the name of the form
        String formName = ctx.ID().getText();

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

        return null;
    }

    @Override
    public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {

        //visit the if case
        IfStatement tempIfStatement = new IfStatement(ctx.getStart().getLine());
        Expression ifCaseHandle = (Expression) visit(ctx.expression());

        tempIfStatement.ifStatementAddCase(ifCaseHandle);

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

        String name = ctx.questionParameters().ID().getText();
        Expression type = (Expression) visit(ctx.questionParameters().type());
        String text = ctx.STRING().getText().replace("\"", "");
        return new Question(name, text, type, ctx.getStart().getLine());

    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Expression visitType(QLParser.TypeContext ctx) {

        if(ctx.children.size() > 1){

            return new GiveValEqual(evaluateQLType(ctx.children.get(0).getText()), (Expression) visit(ctx.expression()) );

        }else {
            return new SimpleTypeValue(evaluateQLType(ctx.getText()));
        }

    }

    private Type evaluateQLType(String tempType) {
        switch (tempType) {
            case "boolean":
                return new Type_bool(false);
            case "integer":
                return new Type_int("0");
            case "string":
                return new Type_str("");
            case "money":
                return new Type_mon("0.0");
            default:
                return new Type_wrongtype();
        }

    }

    @Override
    public Object visitGreaterSmallerEqqual(QLParser.GreaterSmallerEqqualContext ctx) {

        if (ctx.op.getText().equals("<")) {
            return new Smaller((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else if (ctx.op.getText().equals(">")){
            return new Greater((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else if (ctx.op.getText().equals("<=")){
            return new SmallerEq((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else if (ctx.op.getText().equals(">=")){
            return new GreaterEq((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else {
            return new Equal((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitParens(QLParser.ParensContext ctx) {

        return visit(ctx.expression());

    }

    @Override
    public Object visitBool(QLParser.BoolContext ctx) {

        if (ctx.getText().equals("true")) {
            return new Type_bool(true);
        }else {
            return new Type_bool(false);
        }
    }

    @Override
    public Object visitMulDiv(QLParser.MulDivContext ctx) {

        if (ctx.op.getText().equals("*")) {
            return new Mul((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else {
            return new Div((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitAddSub(QLParser.AddSubContext ctx) {

        if (ctx.op.getText().equals("+")) {
            return new Add((Expression)visit(ctx.expression(0)), (Expression) visit(ctx.expression(1) ));
        }else {
            return new Sub((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitId(QLParser.IdContext ctx) {

        return new IdValue(ctx.ID().getText());

    }

    @Override
    public Object visitAndOr(QLParser.AndOrContext ctx) {

        if (ctx.op.getText().equals("AND")) {
            return new And((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else {
            return new Or((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitNumber(QLParser.NumberContext ctx) {

        if (ctx.NUMBER().toString().contains(".")) {
            return new Type_mon( ctx.NUMBER().toString() );
        }else {
            return new Type_int( ctx.NUMBER().toString() );
        }
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}


