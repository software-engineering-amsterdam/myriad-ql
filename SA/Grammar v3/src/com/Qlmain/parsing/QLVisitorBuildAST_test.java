package com.Qlmain.parsing;

import com.Qlmain.QL.*;
import com.Qlmain.Types_Of_Expr.Boolean_ops.*;
import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Number_ops.*;
import com.Qlmain.Types_Of_Expr.Strings.IdValue;
import com.Qlmain.Types_Of_Expr.Strings.SimpleTypeValue;
import com.Qlmain.Types_Of_Expr.Type;
import com.Qlmain.antlr.*;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;


public class QLVisitorBuildAST_test extends AbstractParseTreeVisitor implements QLVisitor {

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
        for (Statement st : form.getStatementList()) {
            //
            // System.out.println(st.getClass() + " " + st.getClass().equals(Question.class));
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

        //Expr ifCaseHandle = (Expr) visit(ctx.expression());

        Expression ifCaseHandle = (Expression) visit(ctx.expression());
        //System.out.println(ifCaseHandler.exprVisitor(ifCaseHandler));

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

        //grab the question metadata and return the object
        String name = ctx.questionParameters().ID().getText();
        //Expr type = new Expr (ctx.getStart().getLine());
        Expression type = (Expression) visit(ctx.questionParameters().type());
        //System.out.println("oh " + type.getLine());
        String text = ctx.STRING().getText().replace("\"", "");
        return new Question(name, text, type, ctx.getStart().getLine());

    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Expression visitType(QLParser.TypeContext ctx) {
        //Expr_Interface typeCheck = new Expr(ctx.getStart().getLine());
        //System.out.println(ctx.getStart().getLine() + " " + ctx.getStop().getLine());

        if(ctx.children.size() > 1){

            return new giveValEqual(evaluateQLType(ctx.children.get(0).getText()), (Expression) visit(ctx.expression()) );

        }else {
            return new SimpleTypeValue(evaluateQLType(ctx.getText()));
        }

    }

    private Type evaluateQLType(String tempType) {
        if (tempType.equals("boolean"))
            return Type.BOOLEAN;
        else if (tempType.equals("integer"))
            return Type.INTEGER;
        else if (tempType.equals("string"))
            return Type.STRING;
        else if (tempType.equals("money"))
            return Type.MONEY;
        else
            return Type.WRONGTYPE;
    }

    @Override
    public Object visitGreaterSmallerEqqual(QLParser.GreaterSmallerEqqualContext ctx) {
        //Expr typeGreaterSmallerEqqual = new Expr(ctx.getStart().getLine());
        //if (ctx.children.size() != 1) {

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

        //}
        //return typeGreaterSmallerEqqual;
    }

    @Override
    public Object visitParens(QLParser.ParensContext ctx) {
        //if (ctx.children.size() != 1) {
        return visit(ctx.expression());
        //}
        //return (Expr) visit(ctx.expression());
    }

    @Override
    public Object visitBool(QLParser.BoolContext ctx) {
        //Expr bool = new Expr(ctx.getStart().getLine());
        if (ctx.getText().equals("true")) {
            return new TypeBoolean(true);
        }else {
            return new TypeBoolean(false);
        }
    }

    @Override
    public Object visitMulDiv(QLParser.MulDivContext ctx) {

        //Expr typeMulDiv = new Expr(ctx.getStart().getLine());

        if (ctx.op.getText().equals("*")) {
            return new Mul((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else {
            return new Div((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitAddSub(QLParser.AddSubContext ctx) {
        //Expr typeAddSub = new Expr(ctx.getStart().getLine());

        if (ctx.op.getText().equals("+")) {
            return new Add((Expression)visit(ctx.expression(0)), (Expression) visit(ctx.expression(1) ));
            //return typeAddSub.new Add((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }else {
            return new Sub((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Object visitId(QLParser.IdContext ctx) {

        //Expr typeId = new Expr(ctx.getStart().getLine());
        return new IdValue(ctx.ID().getText());

    }

    @Override
    public Object visitAndOr(QLParser.AndOrContext ctx) {
        //Expr typeAndOr = new Expr(ctx.getStart().getLine());
        //Expression ex =  Expression;
        //if (ctx.children.size() != 1) {
        if (ctx.op.getText().equals("AND")) {
            return new And((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }else {
            return new Or((Expression) visit(ctx.expression(0)), (Expression) visit(ctx.expression(1)) );
        }
        //}
        //return typeAndOr;
    }

    @Override
    public Object visitNumber(QLParser.NumberContext ctx) {
        //Expr typeInt = new Expr(ctx.getStart().getLine());
        if (ctx.NUMBER().toString().contains(".")) {
            //System.out.println(ctx.getStart().getLine());
            return new MoneyValue( ctx.NUMBER().toString() );
        }else {
            //return typeInt.new IntValue( ctx.NUMBER().toString() );
            return new IntValue( ctx.NUMBER().toString() );
        }
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}


