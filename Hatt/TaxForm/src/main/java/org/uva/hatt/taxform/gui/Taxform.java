package org.uva.hatt.taxform.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.parsing.ASTBuilder;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.typechecker.SemanticAnalyzer;
import org.uva.hatt.taxform.typechecker.messages.Messages;

import java.io.IOException;

public class TaxForm extends Application {

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        QLEditor qlEditor = new QLEditor();

        HBox hBox = new HBox();
        Button createFormButton = createFormButton(stage, qlEditor);

        hBox.getChildren().add(createFormButton);

        VBox vBox = new VBox();
        VBox.setVgrow(qlEditor.getScrollPane(), Priority.ALWAYS);
        vBox.getChildren().addAll(hBox, qlEditor.getScrollPane());

        Scene scene = new Scene(vBox, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Ql Form Editor");
        stage.show();
    }

    private Button createFormButton(Stage stage, QLEditor qlEditor) {
        Button createFormButton = new Button("Create form");

        createFormButton.setOnAction(e -> buildFormAction(stage, qlEditor));

        return createFormButton;
    }

    private void buildFormAction(Stage stage, QLEditor qlEditor) {
        Form form = createForm(qlEditor);

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        Messages messages = semanticAnalyzer.analyze(form);

        if (messages.getErrors().isEmpty()) {

            if (!messages.getWarnings().isEmpty()) {
                showWarningDialog(stage, messages);
            }

            Environment environmentsTable = new Environment();
            FormVisitor uiVisitor = new UIVisitor(stage, environmentsTable);
            uiVisitor.visit(form);

            stage.show();
        } else {
            showErrorDialog(stage, messages);
        }
    }

    private void showWarningDialog(Stage stage, Messages messages) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.NONE);
        dialog.initOwner(stage);

        VBox dialogVbox = new VBox(10);

        messages.getWarnings().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

        Scene dialogScene = new Scene(dialogVbox, 450, 300);

        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void showErrorDialog(Stage stage, Messages messages) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        VBox dialogVbox = new VBox(10);

        messages.getErrors().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

        Scene dialogScene = new Scene(dialogVbox, 450, 300);

        dialog.setScene(dialogScene);
        dialog.show();
    }

    private Form createForm(QLEditor qlEditor) {
        ParseTree tree = null;
        try {
            tree = ASTGenerator.getParseTree(qlEditor.getCodeArea().getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ASTBuilder visitor = new ASTBuilder();
        visitor.visit(tree);

        return visitor.getForm();
    }
}