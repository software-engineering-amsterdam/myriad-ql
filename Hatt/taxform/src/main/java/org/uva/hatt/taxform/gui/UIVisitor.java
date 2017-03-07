package org.uva.hatt.taxform.gui;

import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.FormId;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.ComputationExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.Conditional;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import javax.script.ScriptException;
import java.util.List;
import java.util.stream.Collectors;

public class UIVisitor implements Visitor<Pane> {

    private Stage stage;

    public UIVisitor(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Pane visit(Form node) {
        stage.setTitle(node.getFormId());

        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 800, 600);

        List<Pane> questions = node.getQuestions().stream().map(item -> item.accept(this)).collect(Collectors.toList());

        vBox.getChildren().addAll(questions);

        stage.setScene(scene);
        stage.sizeToScene();

        return null;
    }

    @Override
    public Pane visit(FormId node) {
        return null;
    }

    @Override
    public Pane visit(Question node) {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Text(node.getQuestion()));

        Pane widget = node.getType().accept(this);

        vBox.getChildren().add(widget);

        return vBox;
    }

    @Override
    public Pane visit(Conditional node) {
        VBox vBox = new VBox();
        java.lang.Boolean condition = null;
        try {
            condition = java.lang.Boolean.valueOf(node.getCondition().evaluate());
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        if (condition) {
            List<Pane> thenStatements = node.getThenStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
            vBox.getChildren().addAll(thenStatements);
        } else {
            List<Pane> elseStatements = node.getElseStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
            vBox.getChildren().addAll(elseStatements);
        }

        return vBox;
    }

    @Override
    public Pane visit(Boolean node) {
        HBox pane = new HBox();

        RadioButton yes = new RadioButton("Yes");
        RadioButton no = new RadioButton("No");

        pane.getChildren().addAll(yes, no);

        return pane;
    }

    @Override
    public Pane visit(Integer node) {
        VBox pane = new VBox();
        pane.getChildren().add(new TextField("Vul in!"));

        return pane;
    }

    @Override
    public Pane visit(Money node) {
        VBox pane = new VBox();
        pane.getChildren().add(new TextField("Vul in!"));

        return pane;
    }

    @Override
    public Pane visit(String node) {
        VBox pane = new VBox();
        pane.getChildren().add(new TextField("Vul in!"));

        return pane;
    }

    @Override
    public Pane visit(ValueType node) {
        return null;
    }

    @Override
    public Pane visit(BooleanExpression node) {
        return null;
    }

    @Override
    public Pane visit(ComputationExpression node) {
        return null;
    }

    @Override
    public Pane visit(GroupedExpression node) {
        return null;
    }

    @Override
    public Pane visit(Identifier identifier) {
        return null;
    }

    @Override
    public Pane visit(StringerLiteral stringerLiteral) {
        return null;
    }

    @Override
    public Pane visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Pane visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Pane visit(Expression expression) {
        return null;
    }


}
