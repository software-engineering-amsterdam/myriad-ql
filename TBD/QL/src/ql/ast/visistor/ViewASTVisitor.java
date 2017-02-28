package ql.ast.visistor;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ql.ast.*;
import ql.ast.visistor.environment.Environment;
import ql.view.FormGenerator;

import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor extends ASTVisitor<VBox> {
    private final Environment environment;
    private final FormGenerator formGenerator;

    public ViewASTVisitor(Environment environment) {
        this.environment = environment;
        this.formGenerator = new FormGenerator(environment);
    }

    public Scene startVisitor(ASTNode node) {
        return new Scene(node.accept(this));
    }

    public VBox visit(Form node) {
        VBox hbox = node.getStatements().accept(this);
        return hbox;
    }


    public VBox visit(Statements node) {

        List<Statement> statements = node.getStatements();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        return null;
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
        return null;
    }



    public VBox visit(QuestionExpr node) {
        node.getExpr().accept(this);
        return null;
    }
}
