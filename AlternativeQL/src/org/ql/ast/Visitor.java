package org.ql.ast;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.ql.ast.statement.Question;
import org.ql.ast.form.Form;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.statement.question.Text;
import org.ql.ast.type.Type;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserVisitor;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends AbstractParseTreeVisitor<Node> implements QLParserVisitor<Node> {

    @Override
    public Node visitForm(QLParserParser.FormContext ctx) {

        List<Statement> statements = new ArrayList<>();

        for (QLParserParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new Form((Identifier) visit(ctx.id), statements);
    }

    @Override
    public Node visitQuestion(QLParserParser.QuestionContext ctx) {
        return new Question(
            (Identifier) visit(ctx.id),
            (Text) visit(ctx.text),
            (Type) visit(ctx.type())
        );
    }

    @Override
    public Node visitTerminal(TerminalNode node) {
        return super.visitTerminal(node);
    }

    @Override
    public Node visitIf(QLParserParser.IfContext ctx) {
        return null;
    }

    @Override
    public Node visitQuestionText(QLParserParser.QuestionTextContext ctx) {
        return new Text(removeQuotes(ctx.getText()));
    }

    private String removeQuotes(String text) {
        return text.substring(1, text.length() - 1);
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
        return new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));
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
