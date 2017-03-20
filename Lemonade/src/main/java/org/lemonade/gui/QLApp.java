package org.lemonade.gui;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.lemonade.QLLexer;
import org.lemonade.QLParser;
import org.lemonade.QLParserErrorListener;
import org.lemonade.exceptions.InvalidFormException;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.GuiVisitor;
import org.lemonade.visitors.TypeCheckVisitor;

import javafx.application.Application;
import javafx.stage.Stage;

public class QLApp extends Application {

    private QLGui qlGui;

    private boolean isSubmitted = false;

    // JavaFX initialisation method
    @Override
    public void start(final Stage primaryStage) throws Exception {

        qlGui = new QLGui(primaryStage);
        qlGui.getSubmitFileButton().setOnAction(e -> {
            if (qlGui.getFile() != null) {
                goToQuestionnaire();
            }
        });

        primaryStage.setScene(qlGui.getSelectionScene());
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Called on file submit
    private void goToQuestionnaire() {
        GuiForm guiRoot;

        try {
            Form root = parseFile(qlGui.getFile());
            validateForm(root);

            GuiVisitor guiVisitor = new GuiVisitor(qlGui);
            guiRoot = (GuiForm) root.accept(guiVisitor);

            qlGui.setUpQuestionnaireScene();
            qlGui.getSubmitQuestionnaireButton().setOnAction(e -> {
                isSubmitted = true;
                submitForm(guiRoot);
            });

            EvaluateVisitor evaluateVisitor = new EvaluateVisitor();
            qlGui.addUserInputListeners(guiRoot, evaluateVisitor);

            qlGui.goToQuestionnaire();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (InvalidFormException e) {
            List<String> formErrors = e.getFormErrors();
            System.err.print(formErrors);
        }
    }

    // Something will happen here (evaluate visitor)
    private void submitForm(GuiForm guiForm) {
        System.err.println("In submit");
    }

    private Form parseFile(final File file) throws IOException {
        final String contents = String.join("\n", Files.readAllLines(Paths.get(file.getPath())));

        ANTLRInputStream input = new ANTLRInputStream(new StringReader(contents));
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        QLParserErrorListener errorListener = new QLParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ParseTree tree = parser.form();

        FormVisitor visitor = new FormVisitor();
        return (Form) tree.accept(visitor);
    }

    private void validateForm(Form root) throws InvalidFormException {
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        root.accept(typeCheckVisitor);

        if (typeCheckVisitor.hasErrors()) {
            throw new InvalidFormException(typeCheckVisitor.getErrors());
        }
    }
}
