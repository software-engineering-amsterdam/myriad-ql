package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.items.Conditional;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.grammars.QLBaseVisitor;
import org.uva.hatt.taxform.grammars.QLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QLVisitor extends QLBaseVisitor<ASTNode>{

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
    public ASTNode visitConditional(QLParser.ConditionalContext ctx) {
        Conditional conditional = new Conditional(ctx.start.getLine());

        conditional.setCondition((Expression) visit(ctx.ifBlock().expression()));
        conditional.setThenStatements(getStatements(ctx.ifBlock().items()));
        conditional.setElseStatements(ctx.elseBlock() == null ? new ArrayList<>() : getStatements(ctx.elseBlock().items()));

        return conditional;
    }

    private List<Item> getStatements(List<QLParser.ItemsContext> items) {
        return items.stream().map(item -> (Item) visit(item)).collect(Collectors.toList());
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
