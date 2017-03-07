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
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 24/02/2017.
 */
public class AntlrVisitor extends QLBaseVisitor<TreeNode> {

    @Override
    public TreeNode visitFormDeclaration(QLParser.FormDeclarationContext ctx) {
        String ID = ctx.ID().getText();
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new Form(ID, statements);
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext ctx) {
        String questionID = ctx.ID().getText();
        String questionContent = ctx.STRING().getText();

        Type questionReturnType = (Type) visit(ctx.type());
        Expression calculation;

        if (ctx.calculatedValue() != null) {
            calculation = (Expression) visit(ctx.calculatedValue());
        } else {
            calculation = null;
        }

        return new Question(questionID, questionContent, questionReturnType, calculation);
    }

    @Override
    public TreeNode visitAddSub(QLParser.AddSubContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        if (ctx.op.getText().equals("+")) {
            return new Addition(left, right);
        } else {
            return new Subtraction(left, right);
        }
    }

    @Override
    public TreeNode visitParameterGroup(QLParser.ParameterGroupContext ctx) {
        ParameterGroup parameterGroup = new ParameterGroup();
        parameterGroup.addExpression((Expression) visit(ctx.expression()));
        return parameterGroup;
    }

    @Override
    public TreeNode visitMulDiv(QLParser.MulDivContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        if (ctx.op.getText().equals("*")) {
            return new Multiplication(left, right);
        } else {
            return new Division(left, right);
        }
    }

    @Override
    public TreeNode visitComparation(QLParser.ComparationContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        if (ctx.op.getText().equals("<")) {
            return new LessThan(left, right);
        } else if (ctx.op.getText().equals(">")) {
            return new GreaterThan(left, right);
        } else if (ctx.op.getText().equals("<=")) {
            return new LessThanEqualTo(left, right);
        } else if (ctx.op.getText().equals(">=")) {
            return new GreaterThanEqualTo(left, right);
        } else if (ctx.op.getText().equals("!=")) {
            return new NotEqual(left, right);
        } else {
            return new Equal(left, right);
        }

    }

    @Override
    public TreeNode visitIfStatement(QLParser.IfStatementContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new IfStatement((Expression) visit(ctx.expression()), statements);
    }

    @Override
    public TreeNode visitIfElseStatement(QLParser.IfElseStatementContext ctx) {

        List<Statement> ifCaseStatements = new ArrayList<>();
        List<Statement> elseCaseStatements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.ifCase) {
            ifCaseStatements.add((Statement) visit(statementContext));
        }

        for (QLParser.StatementContext statementContext : ctx.elseCase) {
            elseCaseStatements.add((Statement) visit(statementContext));
        }

        return new IfElseStatement((Expression) visit(ctx.expression()), ifCaseStatements, elseCaseStatements);

    }

    @Override
    public TreeNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral();
    }

    @Override
    public TreeNode visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(ctx.ID().getText());
    }

    @Override
    public TreeNode visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalAnd(left, right);
    }

    @Override
    public TreeNode visitLogicalOr(QLParser.LogicalOrContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalOr(left, right);
    }

    @Override
    public TreeNode visitNegation(QLParser.NegationContext ctx) {
        return new Negation((Expression) visit(ctx.expression()));
    }

    @Override
    public TreeNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral();
    }

    @Override
    public TreeNode visitCalculatedValue(QLParser.CalculatedValueContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public TreeNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanLiteral();
    }

    @Override
    public TreeNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerLiteral();
    }

    @Override
    public TreeNode visitStringType(QLParser.StringTypeContext ctx) {
        return new StringLiteral();
    }
}
