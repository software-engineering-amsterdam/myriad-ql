package ql.visistor;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ql.ast.*;
import ql.ast.Expr;
import ql.visistor.environment.Env;
import ql.visistor.interfaces.BaseVisitor;
import ql.gui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor implements BaseVisitor<VBox> {
    private final Env env;

    public ViewASTVisitor(Env env) {
        this.env = env;
    }

    public Scene startVisitor(Form node) {
        return new Scene(node.accept(this));
    }

    public VBox visit(Form node) {
        VBox statements = node.getStatements().accept(this);
        GUIForm formBox = new GUIForm(node.getName(), statements);

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

        return new QUIStatements(statementBoxes.toArray(new VBox[statementBoxes.size()]));
    }

    public VBox visit(If node) {
        Expr condition = node.getCondition();
        VBox statements = node.getIfBlock().accept(this);
        return new GUIIf(env, condition, statements);
    }

    public VBox visit(IfElse node) {
        Expr condition = node.getCondition();
        VBox ifStatements = node.getIfBlock().accept(this);
        VBox elseStatements = node.getElseBlock().accept(this);
        return new GUIIfElse(env, condition, ifStatements, elseStatements);
    }


    public VBox visit(Question node) {
        return new GUIQuestion(env, node.getQuestion(), node.getId());
    }

    public VBox visit(QuestionExpr node) {
        return new GUIQuestion(env, node.getQuestion(), node.getId());
    }
}
