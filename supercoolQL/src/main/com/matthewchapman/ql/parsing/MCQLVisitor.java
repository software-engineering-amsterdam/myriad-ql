package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLBaseVisitor;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.*;
import com.matthewchapman.ql.ast.atomic.BooleanLiteral;
import com.matthewchapman.ql.ast.atomic.IntegerLiteral;
import com.matthewchapman.ql.ast.atomic.StringLiteral;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */
public class MCQLVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitFormDeclaration(QLParser.FormDeclarationContext ctx) {
        String ID = ctx.ID().getText();
        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new Form(ID, statements);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        String questionID = ctx.ID().getText();
        String questionContent = ctx.STRING().getText();
        Type questionReturnType = (Type) visit(ctx.type());
        ParameterGroup parameterGroup = new ParameterGroup();

        if(ctx.calculatedValue() != null) {
            parameterGroup = (ParameterGroup) visit(ctx.calculatedValue());
        }

        return new Question(questionID, questionContent, questionReturnType, parameterGroup);
    }

    @Override
    public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new IfStatement((Expression) visit(ctx.expression()), statements);
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.STRING().getText());
    }

    @Override
    public ASTNode visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(ctx.ID().getText());
    }

    @Override
    public ASTNode visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalAnd(left, right);
    }

    @Override
    public ASTNode visitLogicalOr(QLParser.LogicalOrContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalOr(left, right);
    }

    @Override
    public ASTNode visitNegation(QLParser.NegationContext ctx) {
        return new Negation((Expression) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitSubtraction(QLParser.SubtractionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Subtraction(left, right);
    }

    @Override
    public ASTNode visitNotEqual(QLParser.NotEqualContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new NotEqual(left, right);
    }

    @Override
    public ASTNode visitParameterGroup(QLParser.ParameterGroupContext ctx) {
        ParameterGroup parameterGroup = new ParameterGroup();

        for (QLParser.ExpressionContext expressionContext: ctx.expression()) {
            parameterGroup.addExpression((Expression) visit(expressionContext));
        }

        return parameterGroup;
    }

    @Override
    public ASTNode visitDivision(QLParser.DivisionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Division(left, right);
    }

    @Override
    public ASTNode visitEqual(QLParser.EqualContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Equal(left, right);
    }

    @Override
    public ASTNode visitLessThan(QLParser.LessThanContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LessThan(left, right);
    }

    @Override
    public ASTNode visitGreaterThanEqualTo(QLParser.GreaterThanEqualToContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new GreaterThanEqualTo(left, right);
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.NUMBER().getText());
    }

    @Override
    public ASTNode visitMultiplication(QLParser.MultiplicationContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Multiplication(left, right);
    }

    @Override
    public ASTNode visitAddition(QLParser.AdditionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Addition(left, right);
    }

    @Override
    public ASTNode visitGreaterThan(QLParser.GreaterThanContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new GreaterThan(left, right);
    }

    @Override
    public ASTNode visitLessThanEqualTo(QLParser.LessThanEqualToContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LessThanEqualTo(left, right);
    }

    @Override
    public ASTNode visitCalculatedValue(QLParser.CalculatedValueContext ctx) {
        ParameterGroup parameterGroup = (ParameterGroup) visit(ctx.expression());

        return parameterGroup;
    }

    @Override
    public ASTNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
        return new StringLiteral(ctx.getText());
    }
}
