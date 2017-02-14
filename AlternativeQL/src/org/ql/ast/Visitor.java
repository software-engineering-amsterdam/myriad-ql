package org.ql.ast;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.BooleanLiteral;
import org.ql.ast.expression.FloatLiteral;
import org.ql.ast.expression.IntegerLiteral;
import org.ql.ast.statement.If;
import org.ql.ast.statement.question.Question;
import org.ql.ast.form.Form;
import org.ql.ast.expression.StringLiteral;
import org.ql.ast.statement.Statement;
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
        List<Statement> statements = new ArrayList<>();

        for (QLParserParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new If((Expression) visit(ctx.expression()), statements);
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
    public Node visitNegation(QLParserParser.NegationContext ctx) {
        return null;
    }

    @Override
    public Node visitProduct(QLParserParser.ProductContext ctx) {
        return null;
    }

    @Override
    public Node visitGrouped(QLParserParser.GroupedContext ctx) {
        return null;
    }

    @Override
    public Node visitIncrement(QLParserParser.IncrementContext ctx) {
        return null;
    }

    @Override
    public Node visitSubtraction(QLParserParser.SubtractionContext ctx) {
        return null;
    }

    @Override
    public Node visitNotEqual(QLParserParser.NotEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitLogicalAnd(QLParserParser.LogicalAndContext ctx) {
        return null;
    }

    @Override
    public Node visitLowerThan(QLParserParser.LowerThanContext ctx) {
        return null;
    }

    @Override
    public Node visitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitDivision(QLParserParser.DivisionContext ctx) {
        return null;
    }

    @Override
    public Node visitParameter(QLParserParser.ParameterContext ctx) {
        return new Parameter(ctx.ID().getText());
    }

    @Override
    public Node visitIdentifier(QLParserParser.IdentifierContext ctx) {
        return new Identifier(ctx.ID().getText());
    }

    @Override
    public Node visitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));
    }

    @Override
    public Node visitAddition(QLParserParser.AdditionContext ctx) {
        return null;
    }

    @Override
    public Node visitGreaterThan(QLParserParser.GreaterThanContext ctx) {
        return null;
    }

    @Override
    public Node visitStringLiteral(QLParserParser.StringLiteralContext ctx) {
        return new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));
    }

    @Override
    public Node visitDecrement(QLParserParser.DecrementContext ctx) {
        return null;
    }

    @Override
    public Node visitEquals(QLParserParser.EqualsContext ctx) {
        return null;
    }

    @Override
    public Node visitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitFloatLiteral(QLParserParser.FloatLiteralContext ctx) {
        return new FloatLiteral(Float.parseFloat(ctx.FLOAT_LITERAL().getText()));
    }

    @Override
    public Node visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
    }

    @Override
    public Node visitLogicalOr(QLParserParser.LogicalOrContext ctx) {
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
