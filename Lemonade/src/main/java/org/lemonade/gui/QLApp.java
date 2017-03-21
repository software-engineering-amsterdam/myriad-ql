package org.lemonade.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.lemonade.QLLexer;
import org.lemonade.QLParser;
import org.lemonade.QLParserErrorListener;
import org.lemonade.exceptions.InvalidFormException;
import org.lemonade.exceptions.TypeMismatchException;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.GuiVisitor;
import org.lemonade.visitors.TypeCheckVisitor;
import org.lemonade.visitors.JsonVisitor;

import javafx.application.Application;
import javafx.stage.Stage;

public class QLApp extends Application implements ButtonCallback {

    private QLGui qlGui;
    private GuiForm guiRoot;

    // JavaFX initialisation method
    @Override
    public void start(final Stage primaryStage) throws Exception {
        // Pass 'this' (implementation of ButtonCallback) as argument to assign callback to button
        qlGui = new QLGui(primaryStage, this);
    }

    // Called on file submit
    @Override
    public void goToQuestionnaire(File file) {
        try {
            Form root = parseFile(file);
            validateForm(root);

            GuiVisitor guiVisitor = new GuiVisitor(qlGui);
            guiRoot = (GuiForm) root.accept(guiVisitor);

            // Pass 'this' (implementation of ButtonCallback) as argument to assign callback to button
            qlGui.setUpQuestionnaireScene(this);

            EvaluateVisitor evaluateVisitor = new EvaluateVisitor();
            qlGui.addUserInputListeners(guiRoot, evaluateVisitor);

            qlGui.goToQuestionnaire();
        } catch (IOException e) {
            qlGui.addErrors("Error reading file: ", Collections.singletonList(e.getMessage()));
        } catch (InvalidFormException e) {
            qlGui.addErrors("Errors found while parsing form:", e.getFormErrors());
        } catch (TypeMismatchException e) {
            qlGui.addErrors("Type mismatches found in form:", e.getTypeMismatches());
        }
    }

    // Writes all answers to JSON file
    @Override
    public void submitForm(File file) {
        if (guiRoot != null) {
            JsonVisitor jsonVisitor = new JsonVisitor();
            guiRoot.accept(jsonVisitor);

            try {
                FileWriter writer = new FileWriter(file);
                writer.write(jsonVisitor.getJSONString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                // TODO something here
            }
        }
    }

    private Form parseFile(final File file) throws IOException, InvalidFormException {
        final String contents = String.join("\n", Files.readAllLines(Paths.get(file.getPath())));

        ANTLRInputStream input = new ANTLRInputStream(new StringReader(contents));
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        QLParserErrorListener errorListener = new QLParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ParseTree tree = parser.form();

        if (errorListener.hasErrors()) {
            throw new InvalidFormException(errorListener.getItems());
        }

        FormVisitor visitor = new FormVisitor();
        return (Form) tree.accept(visitor);
    }

    private void validateForm(Form root) throws TypeMismatchException {
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        root.accept(typeCheckVisitor);

        if (typeCheckVisitor.hasErrors()) {
            throw new TypeMismatchException(typeCheckVisitor.getErrors());
        }
    }
}
