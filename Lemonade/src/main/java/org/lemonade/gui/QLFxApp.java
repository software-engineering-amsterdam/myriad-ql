package org.lemonade.gui;

import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.lemonade.QLLexer;
import org.lemonade.QLParser;
import org.lemonade.QLParserErrorListener;
import org.lemonade.gui.elements.GuiForm;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.GuiVisitor;
import org.lemonade.visitors.TypeCheckVisitor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QLFxApp extends Application {

    private File file;
    private Scene selectionScene;
    private Scene questionnaireScene;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Form");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("Select a .ql file", "*.ql")));

        Label fileLabel = new Label();

        final Button openButton = new Button("Select a questionnaire");
        openButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                fileLabel.setText(file.getPath());
            }
        });

        final Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (file != null) {
                goToQuestionnaire(file, primaryStage);
            }
        });

        AnchorPane anchorPane = new AnchorPane();
        HBox hBox = new HBox();
        //        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(openButton, submitButton, fileLabel);
        AnchorPane.setBottomAnchor(hBox, 10.0);
        AnchorPane.setLeftAnchor(hBox, 5.0);
        anchorPane.getChildren().add(hBox);
        anchorPane.setPadding(new Insets(10, 10, 10, 10));

        selectionScene = new Scene(anchorPane);

        primaryStage.setScene(selectionScene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void goToQuestionnaire(File file, Stage stage) {
        String contents = null;

        try {
            final Button submitButton = new Button("Submit form");
            final Button backButton = new Button("Select new questionnaire");
            backButton.setOnAction(e -> stage.setScene(selectionScene));

            final GridPane gridPane = new GridPane();
            gridPane.setHgap(6);
            gridPane.setVgap(6);

            final AnchorPane rootGroup = new AnchorPane();
            AnchorPane.setBottomAnchor(submitButton, 10.0);
            AnchorPane.setLeftAnchor(submitButton, 5.0);
            AnchorPane.setBottomAnchor(backButton, 10.0);
            AnchorPane.setRightAnchor(backButton, 5.0);
            AnchorPane.setTopAnchor(gridPane, 10.0);
            AnchorPane.setLeftAnchor(gridPane, 5.0);

            rootGroup.getChildren().addAll(gridPane, backButton, submitButton);
            rootGroup.setPadding(new Insets(10, 10, 10, 10));

            questionnaireScene = new Scene(rootGroup);

            contents = String.join("\n", Files.readAllLines(Paths.get(file.getPath())));
            System.out.println(contents);

            ANTLRInputStream input = new ANTLRInputStream(new StringReader(contents));
            QLLexer lexer = new QLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            QLParserErrorListener errorListener = new QLParserErrorListener();
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            ParseTree tree = parser.form();

            FormVisitor visitor = new FormVisitor();
            Form root = (Form) tree.accept(visitor);

            TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
            GuiVisitor guiVisitor = new GuiVisitor(gridPane);

            root.accept(typeCheckVisitor);
            GuiForm guiRoot = (GuiForm) root.accept(guiVisitor);

            submitButton.setOnAction(e -> submitForm(guiRoot, gridPane));

            stage.setScene(questionnaireScene);

        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    // Something will happen here (evaluate visitor)
    private void submitForm(GuiForm guiForm, GridPane gridPane) {
        Node node = gridPane.getChildren().get(1);
        if (node.isVisible())
            node.setVisible(false);
        else
            node.setVisible(true);
        System.err.println("In submit");
    }
}
