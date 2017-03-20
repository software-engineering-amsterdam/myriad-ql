package org.ql.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.*;
import org.ql.grammar.QLLexer;
import org.ql.grammar.QLParser;
import org.ql.grammar.QLVisitor;

import static org.ql.ast.SourceLocationHydrator.*;
import static org.ql.ast.StringUnquoter.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QLASTBuilder extends AbstractParseTreeVisitor<Node> implements QLVisitor<Node> {

    public Form buildAST(InputStream inputStream) throws IOException {
        return visitForm(createParser(inputStream).form());
    }

    private QLParser createParser(InputStream inputStream) throws IOException {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return hydrateSourceLocation(new Form((Identifier) visit(ctx.id), statements), ctx);
    }

    @Override
    public ComputableQuestion visitComputableQuestion(QLParser.ComputableQuestionContext ctx) {
        ComputableQuestion question = new ComputableQuestion(
            (Identifier) visit(ctx.id),
            (QuestionLabel) visit(ctx.text),
            (Type) visit(ctx.type()),
            (Expression) visit(ctx.expression())
        );

        return hydrateSourceLocation(question, ctx);
    }

    @Override
    public Question visitBlankQuestion(QLParser.BlankQuestionContext ctx) {
        Question question = new Question(
            (Identifier) visit(ctx.id),
            (QuestionLabel) visit(ctx.text),
            (Type) visit(ctx.type())
        );

        return hydrateSourceLocation(question, ctx);
    }

    @Override
    public IfThen visitIfThen(QLParser.IfThenContext ctx) {
        IfThen ifThen = new IfThen(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements)
        );

        return hydrateSourceLocation(ifThen, ctx);
    }

    @Override
    public IfThenElse visitIfThenElse(QLParser.IfThenElseContext ctx) {
        IfThenElse ifThenElse = new IfThenElse(
                (Expression) visit(ctx.expression()),
                createStatements(ctx.thenStatements),
                createStatements(ctx.elseStatements)
        );

        return hydrateSourceLocation(ifThenElse, ctx);
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
        return hydrateSourceLocation(new QuestionLabel(unquoteString(ctx.getText())), ctx);
    }

    @Override
    public DecimalLiteral visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return hydrateSourceLocation(new DecimalLiteral(new BigDecimal(ctx.DECIMAL_LITERAL().getText())), ctx);
    }

    @Override
    public Negation visitNegation(QLParser.NegationContext ctx) {
        return hydrateSourceLocation(new Negation((Expression) visit(ctx.expression())), ctx);
    }

    @Override
    public Product visitProduct(QLParser.ProductContext ctx) {
        return hydrateSourceLocation(new Product((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public Increment visitIncrement(QLParser.IncrementContext ctx) {
        return hydrateSourceLocation(new Increment((Expression) visit(ctx.expression())), ctx);
    }

    @Override
    public Subtraction visitSubtraction(QLParser.SubtractionContext ctx) {
        return hydrateSourceLocation(new Subtraction((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public NotEqual visitNotEqual(QLParser.NotEqualContext ctx) {
        return hydrateSourceLocation(new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public LogicalAnd visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        return hydrateSourceLocation(new LogicalAnd((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public LowerThan visitLowerThan(QLParser.LowerThanContext ctx) {
        return hydrateSourceLocation(new LowerThan((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public GreaterThanOrEqual visitGreaterThanOrEqual(QLParser.GreaterThanOrEqualContext ctx) {
        return hydrateSourceLocation(new GreaterThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public Division visitDivision(QLParser.DivisionContext ctx) {
        return hydrateSourceLocation(new Division((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public Parameter visitParameter(QLParser.ParameterContext ctx) {
        return hydrateSourceLocation(new Parameter(new Identifier(ctx.ID().getText())), ctx);
    }

    @Override
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) {
        return hydrateSourceLocation(new Identifier(ctx.ID().getText()), ctx);
    }

    @Override
    public BooleanLiteral visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return hydrateSourceLocation(new BooleanLiteral(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText())), ctx);
    }

    @Override
    public Group visitGroup(QLParser.GroupContext ctx) {
        return hydrateSourceLocation(new Group((Expression) visit(ctx.expression())), ctx);
    }

    @Override
    public Addition visitAddition(QLParser.AdditionContext ctx) {
        return hydrateSourceLocation(new Addition((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public GreaterThan visitGreaterThan(QLParser.GreaterThanContext ctx) {
        return hydrateSourceLocation(new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public StringLiteral visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return hydrateSourceLocation(new StringLiteral(unquoteString(ctx.STRING_LITERAL().getText())), ctx);
    }

    @Override
    public Decrement visitDecrement(QLParser.DecrementContext ctx) {
        return hydrateSourceLocation(new Decrement((Expression) visit(ctx.expression())), ctx);
    }

    @Override
    public Equals visitEquals(QLParser.EqualsContext ctx) {
        return hydrateSourceLocation(new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public LowerThanOrEqual visitLowerThanOrEqual(QLParser.LowerThanOrEqualContext ctx) {
        return hydrateSourceLocation(new LowerThanOrEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public IntegerLiteral visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return hydrateSourceLocation(new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText())), ctx);
    }

    @Override
    public LogicalOr visitLogicalOr(QLParser.LogicalOrContext ctx) {
        return hydrateSourceLocation(new LogicalOr((Expression) visit(ctx.left), (Expression) visit(ctx.right)), ctx);
    }

    @Override
    public BooleanType visitTypeBoolean(QLParser.TypeBooleanContext ctx) {
        return hydrateSourceLocation(new BooleanType(), ctx);
    }

    @Override
    public FloatType visitTypeFloat(QLParser.TypeFloatContext ctx) {
        return hydrateSourceLocation(new FloatType(), ctx);
    }

    @Override
    public IntegerType visitTypeInteger(QLParser.TypeIntegerContext ctx) {
        return hydrateSourceLocation(new IntegerType(), ctx);
    }

    @Override
    public StringType visitTypeString(QLParser.TypeStringContext ctx) {
        return hydrateSourceLocation(new StringType(), ctx);
    }

    @Override
    public MoneyType visitTypeMoney(QLParser.TypeMoneyContext ctx) {
        return hydrateSourceLocation(new MoneyType(), ctx);
    }

    @Override
    public DateType visitTypeDate(QLParser.TypeDateContext ctx) {
        return hydrateSourceLocation(new DateType(), ctx);
    }
}
