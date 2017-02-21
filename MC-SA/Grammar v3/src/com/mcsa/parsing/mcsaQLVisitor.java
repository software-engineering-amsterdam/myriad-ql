package com.mcsa.parsing;

import com.mcsa.QL.Form;
import com.mcsa.QL.Node;
import com.mcsa.QL.Question;
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
        ArrayList<Question> statementList = new ArrayList<>();
        
        for (QLParser.StatementContext statementContext : ctx.statement())
        {
            Object visitResult = visit(statementContext);
            statementList.add((Question) visitResult);
        }

        return new Form(formName, statementList);
    }

    @Override
    public Node visitStatement(QLParser.StatementContext ctx) {

        if(ctx.question() != null) {
            return new Question(ctx.question().STRING().getText());
        }
        else
        {
            return new Question("This is an if question placeholder");
        }
    }

    @Override
    public Node visitIfStatement(QLParser.IfStatementContext ctx) {
        return new Question("This is an if question placeholder");
    }

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        return null;
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
