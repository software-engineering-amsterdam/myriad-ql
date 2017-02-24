package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLBaseVisitor;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.*;
import com.matthewchapman.ql.ast.expression.LogicalAnd;
import com.matthewchapman.ql.ast.expression.LogicalOr;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.StringLiteral;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */
public class MCQLVisitor extends QLBaseVisitor<Node> {

    @Override
    public Node visitFormDeclaration(QLParser.FormDeclarationContext ctx) {
        String ID = ctx.ID().getText();

        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext: ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }



        return new Form(ID, statements);
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {

        String questionID = ctx.ID().getText();
        String questionContent = ctx.STRING().getText();
        Type questionReturnType = (Type) visit(ctx.type());

        return new Question(questionID, questionContent, questionReturnType);

    }

    @Override
    public Node visitIfStatement(QLParser.IfStatementContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext: ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new IfStatement((Expression) visit(ctx.expression()), statements);

    }

    @Override
    public Node visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.STRING().getText());

    }

    @Override
    public Node visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(ctx.ID().getText());
    }

    @Override
    public Node visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalAnd(left, right);
    }

    @Override
    public Node visitLogicalOr(QLParser.LogicalOrContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalOr(left, right);
    }
}
