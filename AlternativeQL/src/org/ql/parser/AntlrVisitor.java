package org.ql.parser;

import org.antlr.v4.runtime.Token;
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

        AbstractNode form = new Form((Identifier) visit(ctx.id), statements);
        form.setMetadata(extractMetadataFromToken(ctx.start));

        return form;
    }

    @Override
    public Node visitQuestion(QLParserParser.QuestionContext ctx) {
        AbstractNode question = new Question(
            (Identifier) visit(ctx.id),
            (QuestionText) visit(ctx.text),
            (Type) visit(ctx.type()),
            ctx.defaultValue() == null ? null : (Expression) visit(ctx.defaultValue())
        );
        question.setMetadata(extractMetadataFromToken(ctx.start));

        return question;
    }

    @Override
    public Node visitIf(QLParserParser.IfContext ctx) {
        AbstractNode ifNode = new If(
            (Expression) visit(ctx.expression()),
            createStatements(ctx.thenStatements),
            createStatements(ctx.elseStatements)
        );
        ifNode.setMetadata(extractMetadataFromToken(ctx.start));

        return ifNode;
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
        AbstractNode questiontext = new QuestionText(removeQuotes(ctx.getText()));
        questiontext.setMetadata(extractMetadataFromToken(ctx.start));

        return questiontext;
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
        AbstractNode decimalLiteral = new DecimalLiteral(new BigDecimal(ctx.DECIMAL_LITERAL().getText()));
        decimalLiteral.setMetadata(extractMetadataFromToken(ctx.start));

        return decimalLiteral;
    }

    @Override
    public Node visitNegation(QLParserParser.NegationContext ctx) {
        AbstractNode negation = new Negation((Expression) visit(ctx.expression()));
        negation.setMetadata(extractMetadataFromToken(ctx.start));

        return negation;
    }

    @Override
    public Node visitProduct(QLParserParser.ProductContext ctx) {
        AbstractNode product = new Product((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        product.setMetadata(extractMetadataFromToken(ctx.start));

        return product;
    }

    @Override
    public Node visitIncrement(QLParserParser.IncrementContext ctx) {
        AbstractNode increment = new Increment((Expression) visit(ctx.expression()));
        increment.setMetadata(new Metadata(ctx.start.getLine(), ctx.start.getCharPositionInLine()));

        return increment;
    }

    @Override
    public Node visitSubtraction(QLParserParser.SubtractionContext ctx) {
        AbstractNode subtraction = new Subtraction((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        subtraction.setMetadata(extractMetadataFromToken(ctx.start));

        return subtraction;
    }

    @Override
    public Node visitNotEqual(QLParserParser.NotEqualContext ctx) {
        AbstractNode notEqual = new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        notEqual.setMetadata(extractMetadataFromToken(ctx.start));

        return notEqual;
    }

    @Override
    public Node visitLogicalAnd(QLParserParser.LogicalAndContext ctx) {
        AbstractNode logicalAnd = new LogicalAnd((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        logicalAnd.setMetadata(extractMetadataFromToken(ctx.start));

        return logicalAnd;
    }

    @Override
    public Node visitLowerThan(QLParserParser.LowerThanContext ctx) {
        AbstractNode lowerThan = new LowerThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        lowerThan.setMetadata(extractMetadataFromToken(ctx.start));

        return lowerThan;
    }

    @Override
    public Node visitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx) {
        AbstractNode greaterThanOrEqual = new GreaterThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        greaterThanOrEqual.setMetadata(extractMetadataFromToken(ctx.start));

        return greaterThanOrEqual;
    }

    @Override
    public Node visitDivision(QLParserParser.DivisionContext ctx) {
        AbstractNode division = new Division((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        division.setMetadata(extractMetadataFromToken(ctx.start));

        return division;
    }

    @Override
    public Node visitParameter(QLParserParser.ParameterContext ctx) {
        return new Parameter(new Identifier(ctx.ID().getText()));
    }

    @Override
    public Node visitIdentifier(QLParserParser.IdentifierContext ctx) {
        AbstractNode identifier = new Identifier(ctx.ID().getText());
        identifier.setMetadata(extractMetadataFromToken(ctx.start));

        return identifier;
    }

    @Override
    public Node visitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx) {
        AbstractNode booleanLiteral = new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));
        booleanLiteral.setMetadata(extractMetadataFromToken(ctx.start));

        return booleanLiteral;
    }

    @Override
    public Node visitGroup(QLParserParser.GroupContext ctx) {
        AbstractNode group = new Group((Expression) visit(ctx.expression()));
        group.setMetadata(extractMetadataFromToken(ctx.start));

        return group;
    }

    @Override
    public Node visitAddition(QLParserParser.AdditionContext ctx) {
        AbstractNode addition = new Addition((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        addition.setMetadata(extractMetadataFromToken(ctx.start));

        return addition;
    }

    @Override
    public Node visitGreaterThan(QLParserParser.GreaterThanContext ctx) {
        AbstractNode greaterThan = new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        greaterThan.setMetadata(extractMetadataFromToken(ctx.start));

        return greaterThan;
    }

    @Override
    public Node visitStringLiteral(QLParserParser.StringLiteralContext ctx) {
        AbstractNode stringLiteral = new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));
        stringLiteral.setMetadata(extractMetadataFromToken(ctx.start));

        return stringLiteral;
    }

    @Override
    public Node visitDecrement(QLParserParser.DecrementContext ctx) {
        AbstractNode decrement = new Decrement((Expression) visit(ctx.expression()));
        decrement.setMetadata(extractMetadataFromToken(ctx.start));

        return decrement;
    }

    @Override
    public Node visitEquals(QLParserParser.EqualsContext ctx) {
        AbstractNode equals = new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        equals.setMetadata(extractMetadataFromToken(ctx.start));

        return equals;
    }

    @Override
    public Node visitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx) {
        AbstractNode lowerThanOrEqual = new LowerThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        lowerThanOrEqual.setMetadata(extractMetadataFromToken(ctx.start));

        return lowerThanOrEqual;
    }

    @Override
    public Node visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx) {
        AbstractNode integerLiteral = new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
        integerLiteral.setMetadata(extractMetadataFromToken(ctx.start));

        return integerLiteral;
    }

    @Override
    public Node visitLogicalOr(QLParserParser.LogicalOrContext ctx) {
        AbstractNode logicalOr = new LogicalOr((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        logicalOr.setMetadata(extractMetadataFromToken(ctx.start));

        return logicalOr;
    }

    @Override
    public Node visitTypeBoolean(QLParserParser.TypeBooleanContext ctx) {
        AbstractNode booleanType = new BooleanType();
        booleanType.setMetadata(extractMetadataFromToken(ctx.start));

        return booleanType;
    }

    @Override
    public Node visitTypeFloat(QLParserParser.TypeFloatContext ctx) {
        AbstractNode floatType = new FloatType();
        floatType.setMetadata(extractMetadataFromToken(ctx.start));

        return floatType;
    }

    @Override
    public Node visitTypeInteger(QLParserParser.TypeIntegerContext ctx) {
        AbstractNode integerType = new IntegerType();
        integerType.setMetadata(extractMetadataFromToken(ctx.start));

        return integerType;
    }

    @Override
    public Node visitTypeString(QLParserParser.TypeStringContext ctx) {
        AbstractNode stringType = new StringType();
        stringType.setMetadata(extractMetadataFromToken(ctx.start));

        return stringType;
    }

    @Override
    public Node visitTypeMoney(QLParserParser.TypeMoneyContext ctx) {
        AbstractNode moneyType = new MoneyType();
        moneyType.setMetadata(extractMetadataFromToken(ctx.start));

        return moneyType;
    }

    @Override
    public Node visitTypeDate(QLParserParser.TypeDateContext ctx) {
        AbstractNode dateType = new DateType();
        dateType.setMetadata(extractMetadataFromToken(ctx.start));

        return dateType;
    }

    private Metadata extractMetadataFromToken(Token start) {
        return new Metadata(start.getLine(), start.getCharPositionInLine());
    }
}
