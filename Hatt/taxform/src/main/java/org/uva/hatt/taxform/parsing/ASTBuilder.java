package org.uva.hatt.taxform.parsing;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.grammars.QLBaseVisitor;
import org.uva.hatt.taxform.grammars.QLParser;

import java.util.List;
import java.util.stream.Collectors;

public class ASTBuilder extends QLBaseVisitor<ASTNode> {

    private Form form;

    public Form getForm() {
        return form;
    }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        form = new Form(ctx.start.getLine(),
                ctx.Identifier().getText(),
                ctx.items().stream().map(item -> (Item) visit(item)).collect(Collectors.toList())
        );

        return form;
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        return new Question(ctx.start.getLine(),
                getFormattedString(ctx.StringLiteral().getText()),
                ctx.Identifier().getText(),
                (ValueType) visit(ctx.valueType())
        );
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        return new ComputedQuestion(ctx.start.getLine(),
                getFormattedString(ctx.StringLiteral().getText()),
                ctx.Identifier().getText(),
                (ValueType) visit(ctx.valueType()),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public ASTNode visitIfThen(QLParser.IfThenContext ctx) {
        return new IfThen(ctx.start.getLine(),
                (Expression) visit(ctx.ifBlock().expression()),
                getStatements(ctx.ifBlock().items())
        );
    }

    @Override
    public ASTNode visitIfThenElse(QLParser.IfThenElseContext ctx) {
        return new IfThenElse(ctx.start.getLine(),
                (Expression) visit(ctx.ifBlock().expression()),
                getStatements(ctx.ifBlock().items()),
                getStatements(ctx.elseBlock().items())
        );
    }

    @Override
    public ASTNode visitSubtraction(QLParser.SubtractionContext ctx) {
        return new Subtraction(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitNotEqual(QLParser.NotEqualContext ctx) {
        return new NotEqual(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        return new LogicalAnd(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitGreaterThanOrEqual(QLParser.GreaterThanOrEqualContext ctx) {
        return new GreaterThanOrEqual(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitDivision(QLParser.DivisionContext ctx) {
        return new Division(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitEqual(QLParser.EqualContext ctx) {
        return new Equal(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitLessThan(QLParser.LessThanContext ctx) {
        return new LessThan(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitLessThanOrEqual(QLParser.LessThanOrEqualContext ctx) {
        return new LessThanOrEqual(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitMultiplication(QLParser.MultiplicationContext ctx) {
        return new Multiplication(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitLogicalOr(QLParser.LogicalOrContext ctx) {
        return new LogicalOr(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitAddition(QLParser.AdditionContext ctx) {
        return new Addition(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitGreaterThan(QLParser.GreaterThanContext ctx) {
        return new GreaterThan(ctx.start.getLine(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitGroupedExpression(QLParser.GroupedExpressionContext ctx) {
        return new GroupedExpression(ctx.start.getLine(), (Expression) visit(ctx.expression()));
    }

    private List<Item> getStatements(List<QLParser.ItemsContext> items) {
        return items.stream().map(item -> (Item) visit(item)).collect(Collectors.toList());
    }

    @Override
    public ASTNode visitIdentifier(QLParser.IdentifierContext ctx) {
        return new Identifier(ctx.start.getLine(), ctx.getText());
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringerLiteral(ctx.start.getLine(), getFormattedString(ctx.getText()));
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.start.getLine(), java.lang.Integer.parseInt(ctx.getText()));
    }

    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.start.getLine(), java.lang.Boolean.valueOf(ctx.getText()));
    }

    @Override
    public ASTNode visitBoolean(QLParser.BooleanContext ctx) {
        return new Boolean(ctx.start.getLine());
    }

    @Override
    public ASTNode visitInteger(QLParser.IntegerContext ctx) {
        return new Integer(ctx.start.getLine());
    }

    @Override
    public ASTNode visitString(QLParser.StringContext ctx) {
        return new String(ctx.start.getLine());
    }

    @Override
    public ASTNode visitMoney(QLParser.MoneyContext ctx) {
        return new Money(ctx.start.getLine());
    }

    private java.lang.String getFormattedString(java.lang.String string) {
        return string.substring(1, string.length() - 1);
    }

}
