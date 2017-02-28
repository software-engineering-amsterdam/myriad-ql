package ql.ast.visistor;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ql.ast.*;
import ql.ast.environment.Environment;
import ql.view.QLFormBox;
import ql.view.QLQuestionBox;
import ql.view.QLStatementsBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor extends ASTVisitor<VBox> {
    private final Environment environment;

    public ViewASTVisitor(Environment environment) {
        this.environment = environment;
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

        List<Statement> statements = node.getStatements();
        List<VBox> statementBoxes = new ArrayList<>();
        for (Statement statement: statements) {
            VBox stat = statement.accept(this);
            if(stat != null)
                statementBoxes.add(stat);
        }

        QLStatementsBox statementsBox = new QLStatementsBox();
        statementsBox.addStatements(statementBoxes.toArray(new VBox[statementBoxes.size()]));
        return statementsBox;
    }

    public VBox visit(If node) {
        node.getCondition().accept(this);
        node.getIfBlock().accept(this);
        return null;
    }

    public VBox visit(IfElse node) {
        node.getCondition().accept(this);
        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }


    public VBox visit(Question node) {
        System.out.println("1:  " + node.getId());
        return new QLQuestionBox(environment, node.getQuestion(), node.getId());

    }



    public VBox visit(QuestionExpr node) {
        System.out.println("2:  " + node.getId());
        return new QLQuestionBox(environment, node.getQuestion(), node.getId());
    }
}
