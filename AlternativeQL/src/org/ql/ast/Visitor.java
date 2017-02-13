package org.ql.ast;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.declaration.Declaration;
import org.ql.ast.declaration.Question;
import org.ql.ast.form.Form;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserVisitor;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends AbstractParseTreeVisitor<Node> implements QLParserVisitor<Node> {

    @Override
    public Node visitForm(QLParserParser.FormContext ctx) {

        List<Declaration> declarations = new ArrayList<>();

        for (QLParserParser.DeclarationContext declaration : ctx.declaration()) {
            declarations.add((Declaration) visit(declaration));
        }

        return new Form((Identifier) visit(ctx.id), declarations);
    }

    @Override
    public Node visitQuestionDeclaration(QLParserParser.QuestionDeclarationContext ctx) {
        String question = ctx.questionMsg.getText();
        return new Question(
            (Identifier) visit(ctx.id),
            new StringLiteral(question.substring(1, question.length() - 1)),
            (Type) visit(ctx.type())
        );
    }

    @Override
    public Node visitStatementDeclaration(QLParserParser.StatementDeclarationContext ctx) {
        return null;
    }

    @Override
    public Node visitIfStatement(QLParserParser.IfStatementContext ctx) {
        return null;
    }

    @Override
    public Node visitDefaultValue(QLParserParser.DefaultValueContext ctx) {
        return null;
    }

    @Override
    public Node visitExpression(QLParserParser.ExpressionContext ctx) {
        return null;
    }

    @Override
    public Node visitParameter(QLParserParser.ParameterContext ctx) {
        return null;
    }

    @Override
    public Node visitIdentifier(QLParserParser.IdentifierContext ctx) {
        return new Identifier(ctx.ID().getText());
    }

    @Override
    public Node visitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx) {
        return null;
    }

    @Override
    public Node visitStringLiteral(QLParserParser.StringLiteralContext ctx) {
        String text = ctx.STRING_LITERAL().getText();
        return new StringLiteral(text.substring(1, text.length() - 1));
    }

    @Override
    public Node visitFloatLiteral(QLParserParser.FloatLiteralContext ctx) {
        return null;
    }

    @Override
    public Node visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeBoolean(QLParserParser.TypeBooleanContext ctx) {
        return Type.BOOLEAN;
    }

    @Override
    public Node visitTypeFloat(QLParserParser.TypeFloatContext ctx) {
        return Type.FLOAT;
    }

    @Override
    public Node visitTypeInteger(QLParserParser.TypeIntegerContext ctx) {
        return Type.INTEGER;
    }

    @Override
    public Node visitTypeString(QLParserParser.TypeStringContext ctx) {
        return Type.STRING;
    }

    @Override
    public Node visitTypeMoney(QLParserParser.TypeMoneyContext ctx) {
        return Type.MONEY;
    }

    @Override
    public Node visitTypeDate(QLParserParser.TypeDateContext ctx) {
        return Type.DATE;
    }
}
