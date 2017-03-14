package ql.ast.visistor;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ql.ast.*;
import ql.ast.environment.Env;
import ql.ast.environment.Environment;
import ql.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor extends ASTVisitor<VBox> {
    private final Env env;

    public ViewASTVisitor(Env env) {
        this.env = env;
    }

    public Scene startVisitor(ASTNode node) {
        return new Scene(node.accept(this));
    }

    public VBox visit(Form node) {
        VBox statements = node.getStatements().accept(this);
        QLFormBox formBox = new QLFormBox(node.getName(), statements);

        return formBox;
    }


    public VBox visit(Statements node) {
        List<Statement> statements = node.getItems();
        List<VBox> statementBoxes = new ArrayList<>();
        for (Statement statement: statements) {
            VBox stat = statement.accept(this);
            if(stat != null)
                statementBoxes.add(stat);
        }

        return new QLStatementsBox(statementBoxes.toArray(new VBox[statementBoxes.size()]));
    }

    public VBox visit(If node) {
        Expr condition = node.getCondition();
        VBox statements = node.getIfBlock().accept(this);
        return new QLIfBox(env, condition, statements);
    }

    public VBox visit(IfElse node) {
        Expr condition = node.getCondition();
        VBox ifStatements = node.getIfBlock().accept(this);
        VBox elseStatements = node.getElseBlock().accept(this);
        return new QLIfElseBox(env, condition, ifStatements, elseStatements);
    }


    public VBox visit(Question node) {
        return new QLQuestionBox(env, node.getQuestion(), node.getId());
    }

    public VBox visit(QuestionExpr node) {
        return new QLQuestionBox(env, node.getQuestion(), node.getId());
    }
}
