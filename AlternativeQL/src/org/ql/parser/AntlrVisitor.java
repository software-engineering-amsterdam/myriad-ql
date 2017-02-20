package org.ql.parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.*;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.If;
import org.ql.ast.statement.Question;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserVisitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AntlrVisitor extends AbstractParseTreeVisitor<Node> implements QLParserVisitor<Node> {

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
            (QuestionText) visit(ctx.text),
            (Type) visit(ctx.type()),
            ctx.defaultValue() == null ? null : (Expression) visit(ctx.defaultValue())
        );
    }

    @Override
    public Node visitIf(QLParserParser.IfContext ctx) {
        return new If(
            (Expression) visit(ctx.expression()),
            createStatements(ctx.thenStatements),
            createStatements(ctx.elseStatements)
        );
    }

    private List<Statement> createStatements(List<QLParserParser.StatementContext> statementsContext) {
        List<Statement> thenStatements = new ArrayList<>();

        for (QLParserParser.StatementContext statementContext : statementsContext) {
            thenStatements.add((Statement) visit(statementContext));
        }
        return thenStatements;
    }

    @Override
    public Node visitQuestionText(QLParserParser.QuestionTextContext ctx) {
        return new QuestionText(removeQuotes(ctx.getText()));
    }

    private java.lang.String removeQuotes(java.lang.String text) {
        return text.substring(1, text.length() - 1);
    }

    @Override
    public Node visitDefaultValue(QLParserParser.DefaultValueContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Node visitDecimalLiteral(QLParserParser.DecimalLiteralContext ctx) {
        return new DecimalLiteral(new BigDecimal(ctx.DECIMAL_LITERAL().getText()));
    }

    @Override
    public Node visitNegation(QLParserParser.NegationContext ctx) {
        return new Negation((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitProduct(QLParserParser.ProductContext ctx) {
        return new Product((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitIncrement(QLParserParser.IncrementContext ctx) {
        return new Increment((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitSubtraction(QLParserParser.SubtractionContext ctx) {
        return new Subtraction((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitNotEqual(QLParserParser.NotEqualContext ctx) {
        return new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitLogicalAnd(QLParserParser.LogicalAndContext ctx) {
        return new LogicalAnd((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitLowerThan(QLParserParser.LowerThanContext ctx) {
        return new LowerThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx) {
        return new GreaterThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitDivision(QLParserParser.DivisionContext ctx) {
        return new Division((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitParameter(QLParserParser.ParameterContext ctx) {
        return new Parameter(new Identifier(ctx.ID().getText()));
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
    public Node visitGroup(QLParserParser.GroupContext ctx) {
        return new Group((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitAddition(QLParserParser.AdditionContext ctx) {
        return new Addition((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitGreaterThan(QLParserParser.GreaterThanContext ctx) {
        return new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitStringLiteral(QLParserParser.StringLiteralContext ctx) {
        return new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));
    }

    @Override
    public Node visitDecrement(QLParserParser.DecrementContext ctx) {
        return new Decrement((Expression) visit(ctx.expression()));
    }

    @Override
    public Node visitEquals(QLParserParser.EqualsContext ctx) {
        return new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx) {
        return new LowerThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
    }

    @Override
    public Node visitLogicalOr(QLParserParser.LogicalOrContext ctx) {
        return new LogicalOr((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public Node visitTypeBoolean(QLParserParser.TypeBooleanContext ctx) {
        return new BooleanType();
    }

    @Override
    public Node visitTypeFloat(QLParserParser.TypeFloatContext ctx) {
        return new FloatType();
    }

    @Override
    public Node visitTypeInteger(QLParserParser.TypeIntegerContext ctx) {
        return new IntegerType();
    }

    @Override
    public Node visitTypeString(QLParserParser.TypeStringContext ctx) {
        return new StringType();
    }

    @Override
    public Node visitTypeMoney(QLParserParser.TypeMoneyContext ctx) {
        return new MoneyType();
    }

    @Override
    public Node visitTypeDate(QLParserParser.TypeDateContext ctx) {
        return new DateType();
    }
}
