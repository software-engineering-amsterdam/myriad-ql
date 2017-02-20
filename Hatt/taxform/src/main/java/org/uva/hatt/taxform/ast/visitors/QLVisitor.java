package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.Question;
import org.uva.hatt.taxform.ast.nodes.ValueType;
import org.uva.hatt.taxform.grammars.QLBaseVisitor;
import org.uva.hatt.taxform.grammars.QLParser;

public class QLVisitor extends QLBaseVisitor<ASTNode>{

    private Form form;

    public Form getForm() {
        return form;
    }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        form = new Form(ctx.start.getLine());
        form.setFormId(ctx.Identifier().getText());

        ctx.items().forEach(this::visit);

        return form;
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        Question question = new Question(ctx.start.getLine());
        question.setQuestion(ctx.StringLiteral().getText());
        question.setValue(ctx.Identifier().getText());
        question.setType((ValueType) visit(ctx.valueType()));

        form.getQuestions().add(question);

        return question;
    }

    @Override
    public ASTNode visitValueType(QLParser.ValueTypeContext ctx) {
        return new ValueType(ctx.start.getLine(), ctx.getText());
    }


}
