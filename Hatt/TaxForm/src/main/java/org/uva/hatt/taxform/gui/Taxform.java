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
import org.uva.hatt.taxform.evaluation.EnvironmentsTable;
import org.uva.hatt.taxform.parsing.ASTBuilder;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.typechecker.CircularDependencyChecker;
import org.uva.hatt.taxform.typechecker.TypeChecker;
import org.uva.hatt.taxform.typechecker.messages.Message;

import java.io.IOException;

public class Taxform extends Application {

    public static void main(String[] args) {
        launch(args);
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
        ParseTree tree = null;
        try {
            tree = ASTGenerator.getParseTree(qlEditor.getCodeArea().getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ASTBuilder visitor = new ASTBuilder();
        visitor.visit(tree);

        Form form = visitor.getForm();

        Message message = new Message();
        TypeChecker typeChecker = new TypeChecker(message);
        typeChecker.visit(form);

        CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker(message);
        circularDependencyChecker.visit(form);

        if (message.getErrors().isEmpty()) {

            if (!message.getWarnings().isEmpty()) {
                Stage dialog = new Stage();
                dialog.initModality(Modality.NONE);
                dialog.initOwner(stage);

                VBox dialogVbox = new VBox(10);

                message.getWarnings().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

                Scene dialogScene = new Scene(dialogVbox, 450, 300);

                dialog.setScene(dialogScene);
                dialog.show();
            }

            EnvironmentsTable environmentsTable = new EnvironmentsTable();
            FormVisitor uiVisitor = new UIVisitor(stage, environmentsTable);
            uiVisitor.visit(form);

            stage.show();
        } else {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            VBox dialogVbox = new VBox(10);

            message.getErrors().forEach(error -> dialogVbox.getChildren().add(new Text(error.getMessage())));

            Scene dialogScene = new Scene(dialogVbox, 450, 300);

            dialog.setScene(dialogScene);
            dialog.show();
        }
    }
}