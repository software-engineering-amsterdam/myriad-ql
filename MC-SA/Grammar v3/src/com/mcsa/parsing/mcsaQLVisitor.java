package com.mcsa.parsing;

import com.mcsa.QL.*;
import com.mcsa.antlr.*;
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

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            if (statementContext.question() != null) {
                Question result = (Question) visit(statementContext);
                questions.add(result);
            } else if (statementContext.ifStatement() != null) {
                IfStatement result = (IfStatement) visit(statementContext);
                ifStatements.add(result);
            }
        }

        form.replaceQuestions(questions);
        form.replaceIfStatements(ifStatements);

        return form;
    }

    @Override
    public Node visitStatement(QLParser.StatementContext ctx) {

        if (ctx.question() != null) {
            return (Question) visit(ctx.question());
        } else {
            return (IfStatement) visit(ctx.ifStatement());
        }
    }

    @Override
    public Node visitIfStatement(QLParser.IfStatementContext ctx) {

        visit(ctx.ifCase());
        return null;
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {
        String name = ctx.questionParameters().ID().getText();
        String type = ctx.questionParameters().type().getText();
        String text = ctx.STRING().getText();
        return new Question(name, text, new Type(type));
    }

    @Override
    public Node visitIfCase(QLParser.IfCaseContext ctx) {

        IfCase ifCaseCheck = new IfCase();
        /*if (ctx.ifCaseArgs() != null) {
            if ( ifCaseCheck.getRight() == null) {
                ifCaseCheck.addRight(ctx.ifCaseArgs().getText());
            }else {
                ifCaseCheck.addLeft(ctx.ifCaseArgs().getText());
            }
        }*/
        if (ctx.TOKEN() != null) {
            ifCaseCheck.addToken(ctx.TOKEN().getText());
            System.out.println("ddd");
        }
        if (ctx.ifCase() != null){
            for (QLParser.IfCaseContext caseArg : ctx.ifCase()) {
                if ( ifCaseCheck.getRight() == null) {
                    ifCaseCheck.addRight(visit(caseArg));
                }else {
                    ifCaseCheck.addLeft(visit(caseArg));
                }
            }
            System.out.println();
        }
        return null;
    }

    @Override
    public Object visitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {

        if (ctx.ID() != null)
            return ctx.ID().getText();
        else
            return ctx.NUMBER().getText();

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
