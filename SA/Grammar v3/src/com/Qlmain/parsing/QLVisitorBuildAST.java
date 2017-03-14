package com.Qlmain.parsing;

import com.Qlmain.QL.*;
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
        for (Statement st : form.getStatementList()) {
            System.out.println(st.getClass() + " " + st.getClass().equals(Question.class));
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

        Expr ifCaseHandle = (Expr) visit(ctx.expression());
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
        Expr type = (Expr) visit(ctx.questionParameters().type());
        //System.out.println("oh " + type.getLine());
        String text = ctx.STRING().getText().replace("\"", "");
        return new Question(name, text, type, ctx.getStart().getLine());

    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Expr visitType(QLParser.TypeContext ctx) {
        Expr typeCheck = new Expr(ctx.getStart().getLine());
        System.out.println(ctx.getStart().getLine() + " " + ctx.getStop().getLine());
        if ( ctx.children.size() == 1) {

            return typeCheck.new SympleTypeValue(evaluateQLType(ctx.getText()));

        }else if(ctx.children.size() > 1){

            return typeCheck.new giveValEqual(evaluateQLType(ctx.children.get(0).getText()), (Expr) visit(ctx.expression()) );

        }

        return typeCheck;
    }

    private Expr.Type evaluateQLType(String tempType) {
        if (tempType.equals("boolean"))
            return Expr.Type.BOOLEAN;
        else if (tempType.equals("integer"))
            return Expr.Type.INTEGER;
        else if (tempType.equals("string"))
            return Expr.Type.STRING;
        else if (tempType.equals("money"))
            return Expr.Type.MONEY;
        else
            return Expr.Type.WRONGTYPE;
    }

    @Override
    public Object visitGreaterSmallerEqqual(QLParser.GreaterSmallerEqqualContext ctx) {
        Expr typeGreaterSmallerEqqual = new Expr(ctx.getStart().getLine());
        //if (ctx.children.size() != 1) {

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

        //}
        return typeGreaterSmallerEqqual;
    }

    @Override
    public Expr visitParens(QLParser.ParensContext ctx) {
        //if (ctx.children.size() != 1) {
        return (Expr) visit(ctx.expression());
        //}
        //return (Expr) visit(ctx.expression());
    }

    @Override
    public Expr visitBool(QLParser.BoolContext ctx) {
        Expr bool = new Expr(ctx.getStart().getLine());
        if (ctx.getText().equals("true")) {
            return bool.new TypeBoolean(true);
        }else {
            return bool.new TypeBoolean(false);
        }
    }

    @Override
    public Expr visitMulDiv(QLParser.MulDivContext ctx) {

        Expr typeMulDiv = new Expr(ctx.getStart().getLine());

        if (ctx.op.getText().equals("*")) {
            return typeMulDiv.new Mul((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }else {
            return typeMulDiv.new Div((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Expr visitAddSub(QLParser.AddSubContext ctx) {
        Expr typeAddSub = new Expr(ctx.getStart().getLine());

        if (ctx.op.getText().equals("+")) {
            return typeAddSub.new Add((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }else {
            return typeAddSub.new Sub((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }

    }

    @Override
    public Expr visitId(QLParser.IdContext ctx) {

        Expr typeId = new Expr(ctx.getStart().getLine());
        return typeId.new IdValue(ctx.ID().getText());

    }

    @Override
    public Expr visitAndOr(QLParser.AndOrContext ctx) {
        Expr typeAndOr = new Expr(ctx.getStart().getLine());
        //if (ctx.children.size() != 1) {
        if (ctx.op.getText().equals("AND")) {
            return typeAndOr.new And((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }else {
            return typeAndOr.new Or((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)) );
        }
        //}
        //return typeAndOr;
    }

    @Override
    public Object visitNumber(QLParser.NumberContext ctx) {
        Expr typeInt = new Expr(ctx.getStart().getLine());
        if (ctx.NUMBER().toString().contains(".")) {
            //System.out.println(ctx.getStart().getLine());
            return typeInt.new MoneyValue( ctx.NUMBER().toString() );
        }else {
            return typeInt.new IntValue( ctx.NUMBER().toString() );
        }
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}

