package org.uva.hatt.taxform.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;
import org.uva.hatt.taxform.ast.nodes.items.ComputedQuestion;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.IfThenElse;
import org.uva.hatt.taxform.ast.nodes.items.ItemVisitor;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.evaluation.Evaluator;
import org.uva.hatt.taxform.gui.fields.*;
import org.uva.hatt.taxform.values.BooleanValue;
import org.uva.hatt.taxform.values.Value;

import java.util.List;
import java.util.stream.Collectors;

public class UIVisitor implements FormVisitor<Pane>, ItemVisitor<Pane>, TypeVisitor<Pane> {

    private final Stage stage;
    private final Environment environmentsTable;
    private Form form;
    private final Evaluator evaluator;

    UIVisitor(Stage stage, Environment environmentsTable) {
        this.stage = stage;
        this.environmentsTable = environmentsTable;
        evaluator = new Evaluator(environmentsTable);
    }

    @Override
    public Pane visit(Form node) {
        form = node;
        stage.setTitle(node.getFormId());

        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 800, 600);

        List<Pane> questions = node.getQuestions().stream().map(item -> item.accept(this)).collect(Collectors.toList());

        vBox.getChildren().addAll(questions);
        vBox.requestFocus();

        stage.setScene(scene);
        stage.sizeToScene();

        return null;
    }

    @Override
    public Pane visit(org.uva.hatt.taxform.ast.nodes.items.Question node) {
        Field widget = (Field) node.getType().accept(this);
        widget.setIdentifier(node.getIdentifier());
        widget.setLabel(node.getLabel());

        Value value = environmentsTable.find(node.getIdentifier());
        widget.setValue(value);

        Question question = new Question(environmentsTable, this, form);
        question.addField(widget);

        return question;
    }

    @Override
    public Pane visit(ComputedQuestion node) {
        Field widget = (Field) node.getType().accept(this);
        widget.setIdentifier(node.getIdentifier());
        widget.setLabel(node.getLabel());

        Value value = evaluator.visit(node.getComputedValue());
        widget.setValue(value);
        widget.setReadOnly();

        Question question = new Question(environmentsTable, this, form);
        question.addField(widget);

        return question;
    }

    @Override
    public Pane visit(IfThen node) {
        VBox vBox = new VBox();

        Value value = evaluator.visit(node.getCondition());

        if (!value.isUndefined()) {
            BooleanValue booleanValue = (BooleanValue) value;

            if (booleanValue.getValue()) {
                List<Pane> thenStatements = node.getThenStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(thenStatements);
            }
        }

        return vBox;
    }

    @Override
    public Pane visit(IfThenElse node) {
        VBox vBox = new VBox();

        Value value = evaluator.visit(node.getCondition());

        if (!value.isUndefined()) {
            BooleanValue booleanValue = (BooleanValue) value;

            if (booleanValue.getValue()) {
                List<Pane> thenStatements = node.getThenStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(thenStatements);
            } else {
                List<Pane> elseStatements = node.getElseStatements().stream().map(item -> item.accept(this)).collect(Collectors.toList());
                vBox.getChildren().addAll(elseStatements);
            }
        }

        return vBox;
    }

    @Override
    public Pane visit(Boolean node) {
        return new QLCheckBox();
    }

    @Override
    public Pane visit(Integer node) {
        return new QLInteger();
    }

    @Override
    public Pane visit(Money node) {
        return new QLMoney();
    }

    @Override
    public Pane visit(String node) {
        return new QLTextField();
    }

    @Override
    public Pane visit(Unknown unknown) {
        throw new RuntimeException("Found unknown type");
    }

}
