package org.ql.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.*;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.*;
import org.ql.grammar.QLParser;
import org.ql.grammar.QLVisitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends AbstractParseTreeVisitor<Node> implements QLVisitor<Node> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        Form form = new Form((Identifier) visit(ctx.id), statements);
        form.setSourceLocation(extractSourceLocation(ctx));

        return form;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        Question question = new Question(
                (Identifier) visit(ctx.id),
                (QuestionLabel) visit(ctx.text),
                (Type) visit(ctx.type()),
                ctx.value() == null ? null : (Expression) visit(ctx.value())
        );

        question.setSourceLocation(extractSourceLocation(ctx));

        return question;
    }

    @Override
    public IfThen visitIfThen(QLParser.IfThenContext ctx) {
        IfThen ifThen = new IfThen(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements)
        );

        ifThen.setSourceLocation(extractSourceLocation(ctx));

        return ifThen;
    }

    @Override
    public IfThenElse visitIfThenElse(QLParser.IfThenElseContext ctx) {
        IfThenElse ifThenElse = new IfThenElse(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements),
                createStatements(ctx.elseStatements)
        );

        ifThenElse.setSourceLocation(extractSourceLocation(ctx));

        return ifThenElse;
    }

    private List<Statement> createStatements(List<QLParser.StatementContext> statementsContext) {
        List<Statement> thenStatements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : statementsContext) {
            thenStatements.add((Statement) visit(statementContext));
        }
        return thenStatements;
    }

    @Override
    public QuestionLabel visitQuestionLabel(QLParser.QuestionLabelContext ctx) {
        QuestionLabel questionLabel = new QuestionLabel(removeQuotes(ctx.getText()));

        questionLabel.setSourceLocation(extractSourceLocation(ctx));

        return questionLabel;
    }

    private String removeQuotes(String text) {
        return text.substring(1, text.length() - 1);
    }

    @Override
    public Expression visitValue(QLParser.ValueContext ctx) {
        return (Expression) visit(ctx.expression());
    }

    @Override
    public DecimalLiteral visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        DecimalLiteral decimalLiteral = new DecimalLiteral(new BigDecimal(ctx.DECIMAL_LITERAL().getText()));

        decimalLiteral.setSourceLocation(extractSourceLocation(ctx));

        return decimalLiteral;
    }

    @Override
    public Negation visitNegation(QLParser.NegationContext ctx) {
        Negation negation = new Negation((Expression) visit(ctx.expression()));

        negation.setSourceLocation(extractSourceLocation(ctx));

        return negation;
    }

    @Override
    public Product visitProduct(QLParser.ProductContext ctx) {
        Product product = new Product((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        product.setSourceLocation(extractSourceLocation(ctx));

        return product;
    }

    @Override
    public Increment visitIncrement(QLParser.IncrementContext ctx) {
        Increment increment = new Increment((Expression) visit(ctx.expression()));
        increment.setSourceLocation(extractSourceLocation(ctx));

        return increment;
    }

    @Override
    public Subtraction visitSubtraction(QLParser.SubtractionContext ctx) {
        Subtraction subtraction = new Subtraction((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        subtraction.setSourceLocation(extractSourceLocation(ctx));

        return subtraction;
    }

    @Override
    public NotEqual visitNotEqual(QLParser.NotEqualContext ctx) {
        NotEqual notEqual = new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        notEqual.setSourceLocation(extractSourceLocation(ctx));

        return notEqual;
    }

    @Override
    public LogicalAnd visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        LogicalAnd logicalAnd = new LogicalAnd((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        logicalAnd.setSourceLocation(extractSourceLocation(ctx));

        return logicalAnd;
    }

    @Override
    public LowerThan visitLowerThan(QLParser.LowerThanContext ctx) {
        LowerThan lowerThan = new LowerThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        lowerThan.setSourceLocation(extractSourceLocation(ctx));

        return lowerThan;
    }

    @Override
    public GreaterThanOrEqual visitGreaterThanOrEqual(QLParser.GreaterThanOrEqualContext ctx) {
        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        greaterThanOrEqual.setSourceLocation(extractSourceLocation(ctx));

        return greaterThanOrEqual;
    }

    @Override
    public Division visitDivision(QLParser.DivisionContext ctx) {
        Division division = new Division((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        division.setSourceLocation(extractSourceLocation(ctx));

        return division;
    }

    @Override
    public Parameter visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(new Identifier(ctx.ID().getText()));
    }

    @Override
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) {
        Identifier identifier = new Identifier(ctx.ID().getText());
        identifier.setSourceLocation(extractSourceLocation(ctx));

        return identifier;
    }

    @Override
    public BooleanLiteral visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        BooleanLiteral booleanLiteral = new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));

        booleanLiteral.setSourceLocation(extractSourceLocation(ctx));

        return booleanLiteral;
    }

    @Override
    public Group visitGroup(QLParser.GroupContext ctx) {
        Group group = new Group((Expression) visit(ctx.expression()));
        group.setSourceLocation(extractSourceLocation(ctx));
        return group;
    }

    @Override
    public Addition visitAddition(QLParser.AdditionContext ctx) {
        Addition addition = new Addition((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        addition.setSourceLocation(extractSourceLocation(ctx));

        return addition;
    }

    @Override
    public GreaterThan visitGreaterThan(QLParser.GreaterThanContext ctx) {
        GreaterThan greaterThan = new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        greaterThan.setSourceLocation(extractSourceLocation(ctx));

        return greaterThan;
    }

    @Override
    public StringLiteral visitStringLiteral(QLParser.StringLiteralContext ctx) {
        StringLiteral stringLiteral = new StringLiteral(removeQuotes(ctx.STRING_LITERAL().getText()));

        stringLiteral.setSourceLocation(extractSourceLocation(ctx));

        return stringLiteral;
    }

    @Override
    public Decrement visitDecrement(QLParser.DecrementContext ctx) {
        Decrement decrement = new Decrement((Expression) visit(ctx.expression()));

        decrement.setSourceLocation(extractSourceLocation(ctx));

        return decrement;
    }

    @Override
    public Equals visitEquals(QLParser.EqualsContext ctx) {
        Equals equals = new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        equals.setSourceLocation(extractSourceLocation(ctx));

        return equals;
    }

    @Override
    public LowerThanOrEqual visitLowerThanOrEqual(QLParser.LowerThanOrEqualContext ctx) {
        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        lowerThanOrEqual.setSourceLocation(extractSourceLocation(ctx));

        return lowerThanOrEqual;
    }

    @Override
    public IntegerLiteral visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        IntegerLiteral integerLiteral = new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));

        integerLiteral.setSourceLocation(extractSourceLocation(ctx));

        return integerLiteral;
    }

    @Override
    public LogicalOr visitLogicalOr(QLParser.LogicalOrContext ctx) {
        LogicalOr logicalOr = new LogicalOr((Expression) visit(ctx.left), (Expression) visit(ctx.right));

        logicalOr.setSourceLocation(extractSourceLocation(ctx));

        return logicalOr;
    }

    @Override
    public BooleanType visitTypeBoolean(QLParser.TypeBooleanContext ctx) {
        BooleanType booleanType = new BooleanType();
        booleanType.setSourceLocation(extractSourceLocation(ctx));
        return booleanType;
    }

    @Override
    public FloatType visitTypeFloat(QLParser.TypeFloatContext ctx) {
        FloatType floatType = new FloatType();
        floatType.setSourceLocation(extractSourceLocation(ctx));

        return floatType;
    }

    @Override
    public IntegerType visitTypeInteger(QLParser.TypeIntegerContext ctx) {
        IntegerType integerType = new IntegerType();
        integerType.setSourceLocation(extractSourceLocation(ctx));

        return integerType;
    }

    @Override
    public StringType visitTypeString(QLParser.TypeStringContext ctx) {
        StringType stringType = new StringType();
        stringType.setSourceLocation(extractSourceLocation(ctx));

        return stringType;
    }

    @Override
    public MoneyType visitTypeMoney(QLParser.TypeMoneyContext ctx) {
        MoneyType moneyType = new MoneyType();
        moneyType.setSourceLocation(extractSourceLocation(ctx));

        return moneyType;
    }

    @Override
    public DateType visitTypeDate(QLParser.TypeDateContext ctx) {
        DateType dateType = new DateType();
        dateType.setSourceLocation(extractSourceLocation(ctx));

        return dateType;
    }

    private SourceLocation extractSourceLocation(ParserRuleContext context) {
        return new SourceLocation(context.start.getLine(), context.start.getCharPositionInLine());
    }
}
