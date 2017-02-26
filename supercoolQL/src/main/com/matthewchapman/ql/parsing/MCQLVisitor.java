package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLBaseVisitor;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.ASTNode;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.atomic.StringLiteral;
import com.matthewchapman.ql.ast.expression.Expression;
import com.matthewchapman.ql.ast.expression.LogicalAnd;
import com.matthewchapman.ql.ast.expression.LogicalOr;
import com.matthewchapman.ql.ast.expression.Parameter;
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

        return new Question(questionID, questionContent, questionReturnType);
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
}
