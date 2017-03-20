package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.ComputationExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.grammars.QLBaseVisitor;
import org.uva.hatt.taxform.grammars.QLParser;

import java.util.List;
import java.util.stream.Collectors;

public class QLVisitor extends QLBaseVisitor<ASTNode> {

    private Form form;

    public Form getForm() {
        return form;
    }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        form = new Form(ctx.start.getLine());
        form.setFormId(ctx.Identifier().getText());

        form.setQuestions(ctx.items().stream().map(item -> (Item) visit(item)).collect(Collectors.toList()));

        return form;
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        Question question = new Question(ctx.start.getLine());
        question.setQuestion(ctx.StringLiteral().getText());
        question.setValue(ctx.Identifier().getText());
        question.setType((ValueType) visit(ctx.valueType()));

        return question;
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        ComputedQuestion computedQuestion = new ComputedQuestion(ctx.start.getLine());
        computedQuestion.setQuestion(ctx.StringLiteral().getText());
        computedQuestion.setValue(ctx.Identifier().getText());
        computedQuestion.setType((ValueType) visit(ctx.valueType()));
        computedQuestion.setComputedValue((Expression) visit(ctx.expression()));

        return computedQuestion;
    }

    @Override
    public ASTNode visitIfThen(QLParser.IfThenContext ctx) {
        IfThen ifThen = new IfThen(ctx.start.getLine());

        ifThen.setCondition((Expression) visit(ctx.ifBlock().expression()));
        ifThen.setThenStatements(getStatements(ctx.ifBlock().items()));

        return ifThen;
    }

    @Override
    public ASTNode visitIfThenElse(QLParser.IfThenElseContext ctx) {
        IfThenElse ifThenElse = new IfThenElse(ctx.start.getLine());

        ifThenElse.setCondition((Expression) visit(ctx.ifBlock().expression()));
        ifThenElse.setThenStatements(getStatements(ctx.ifBlock().items()));
        ifThenElse.setElseStatements(getStatements(ctx.elseBlock().items()));

        return ifThenElse;
    }

    @Override
    public ASTNode visitComputationExpression(QLParser.ComputationExpressionContext ctx) {
        ComputationExpression computationExpression = new ComputationExpression(ctx.start.getLine());
        computationExpression.setLeft((Expression) visit(ctx.left));
        computationExpression.setRight((Expression) visit(ctx.right));
        computationExpression.setOperator(ctx.op.getText());

        return computationExpression;
    }

    @Override
    public ASTNode visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        BooleanExpression booleanExpression = new BooleanExpression(ctx.start.getLine(), lhs, rhs);
        booleanExpression.setLeft((Expression) visit(ctx.left));
        booleanExpression.setRight((Expression) visit(ctx.right));
        booleanExpression.setOperator(ctx.op.getText());

        return booleanExpression;
    }

    @Override
    public ASTNode visitGroupedExpression(QLParser.GroupedExpressionContext ctx) {
        GroupedExpression groupedExpression = new GroupedExpression(ctx.start.getLine());
        groupedExpression.setExpression((Expression) visit(ctx.expression()));

        return groupedExpression;
    }

    private List<Item> getStatements(List<QLParser.ItemsContext> items) {
        return items.stream().map(item -> (Item) visit(item)).collect(Collectors.toList());
    }

    @Override
    public ASTNode visitIdentifier(QLParser.IdentifierContext ctx) {
        Identifier identifier = new Identifier(ctx.start.getLine(), ctx.getText());

        return identifier;
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        StringerLiteral stringerLiteral = new StringerLiteral(ctx.start.getLine(), ctx.getText());

        return stringerLiteral;
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        IntegerLiteral integerLiteral = new IntegerLiteral(ctx.start.getLine(), ctx.getText());

        return integerLiteral;
    }

    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        BooleanLiteral booleanLiteral = new BooleanLiteral(ctx.start.getLine(), ctx.getText());

        return booleanLiteral;
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
}
