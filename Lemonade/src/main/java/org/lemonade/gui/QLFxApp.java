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
import org.lemonade.nodes.Form;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.TypeCheckVisitor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        final GridPane gridPane = new GridPane();
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(fileLabel, 1, 0);
        GridPane.setConstraints(submitButton, 0, 1, 2, 1);
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.getChildren().addAll(openButton, fileLabel, submitButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(gridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        selectionScene = new Scene(rootGroup, 400, 300);

        primaryStage.setScene(selectionScene);
        primaryStage.show();
    }

    private void goToQuestionnaire(File file, Stage stage) {
        String contents = null;

        try {
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
            EvaluateVisitor evaluateVisitor = new EvaluateVisitor();

            root.accept(typeCheckVisitor);
//            root.accept(evaluateVisitor);

            final TextArea textArea = new TextArea(contents);
            final Button backButton = new Button("Select new questionnaire");
            backButton.setOnAction(e -> stage.setScene(selectionScene));

            final GridPane gridPane = new GridPane();
            GridPane.setConstraints(textArea, 0, 0);
            GridPane.setConstraints(backButton, 0, 1);
            gridPane.setHgap(6);
            gridPane.setVgap(6);
            gridPane.getChildren().addAll(textArea);

            final Pane rootGroup = new VBox(12);
            rootGroup.getChildren().addAll(gridPane, backButton);
            rootGroup.setPadding(new Insets(12, 12, 12, 12));

            questionnaireScene = new Scene(rootGroup, 600, 400);

            stage.setScene(questionnaireScene);

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}
