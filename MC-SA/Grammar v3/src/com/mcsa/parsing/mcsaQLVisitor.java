package com.mcsa.parsing;

import com.mcsa.QL.*;
import com.mcsa.antlr.*;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;


/**
 * Created by matt on 20/02/2017.
 */
public class mcsaQLVisitor extends AbstractParseTreeVisitor implements QLVisitor {

    @Override
    public Node visitFormDeclaration(QLParser.FormDeclarationContext ctx) {

        String formName = ctx.ID().getText();

        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<IfStatement> ifStatements = new ArrayList<>();
        Form form = new Form(formName);
        
        for (QLParser.StatementContext statementContext : ctx.statement())
        {
            if(statementContext.question() != null)
            {
                Question result = (Question) visit(statementContext);
                questions.add(result);
            }
            else if (statementContext.ifStatement() != null)
            {
                IfStatement result = (IfStatement) visit(statementContext);
                ifStatements.add(result);
            }
        }

        form.formAddQuestion(questions);
        form.formAddIfStatement(ifStatements);

        return form;
    }

    @Override
    public Node visitStatement(QLParser.StatementContext ctx) {

        if(ctx.question() != null) {
            return (Question) visit(ctx.question());
        }
        else
        {
            return (IfStatement) visit(ctx.ifStatement());
        }
    }

    @Override
    public Node visitIfStatement(QLParser.IfStatementContext ctx) {
        return new IfStatement("This is an IF statement");
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {
        String name = ctx.questionParameters().ID().getText();
        String type = ctx.questionParameters().type().getText();
        String text = ctx.STRING().getText();
        return new Question(name, text, new Type(type));
    }

    @Override
    public Object visitIfCase(QLParser.IfCaseContext ctx) {
        return null;
    }

    @Override
    public Object visitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {
        return null;
    }

    @Override
    public Object visitQuestionParameters(QLParser.QuestionParametersContext ctx) {
        return null;
    }

    @Override
    public Object visitType(QLParser.TypeContext ctx) {
        return null;
    }

    @Override
    public Object visitMathaction(QLParser.MathactionContext ctx) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}
