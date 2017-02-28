/**
 * ql.java.
 */

package ql;

import ql.astnodes.ASTBuilder;
import ql.astnodes.Form;
import ql.gui.GUI;
import ql.gui.components.FormFrame;
import ql.gui.components.GUIManager;
import ql.gui.components.widgets.WidgetFactory;
import ql.semanticchecker.SemanticChecker;
import ql.gui.formenvironment.ValueData;
import ql.semanticchecker.messagehandling.Message;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.Error;
import ql.semanticchecker.messagehandling.warnings.Warning;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class QL {

    private final String inputFile;

    private static final String ALLOWED_EXTENSION = "ql";

    private QL(String inputFile) throws IOException, IllegalArgumentException {
        this.inputFile = inputFile;
    }

    public static void main(String[] arguments) throws Exception {
        String inputFile = "./src/test.ql";
        QL ql = new QL(inputFile);
        ql.parseAndCheck();
    }

    private void parseAndCheck() throws IOException, IllegalArgumentException{
        if (!fileExists()) {
            throw new IOException();
        }

        if (!correctExtension()) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(inputFile);
        Form qlAST = getAST(qlInputStream);
        ValueData questionStates =  new ValueData();
        Boolean semanticallyCorrect = checkSemanticCorrectness(qlAST, questionStates);

        if(!semanticallyCorrect) {
            System.out.println("ql.ql form is semantically incorrect.");
            System.exit(1);
        } else {
            System.out.println("Create ql.gui...");
            buildGUI(qlAST, questionStates);
        }
    }

    private boolean fileExists() {
        Path path = Paths.get(inputFile);
        return Files.exists(path);
    }

    private boolean correctExtension() {
        String fileExtension = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length());
        return fileExtension.equals(ALLOWED_EXTENSION);
    }

    private Form getAST(InputStream inputStream) throws IOException {
        ASTBuilder qlAstBuilder = new ASTBuilder(inputStream);
        return qlAstBuilder.buildAST();
    }

    private boolean checkSemanticCorrectness(Form qlAST, ValueData questionStates) {
        SemanticChecker semanticChecker = new SemanticChecker(qlAST, questionStates);
        MessageData messages = semanticChecker.getMessages();

        if (messages.getWarnings().size() > 0) {
            List<Warning> warnings = messages.getWarnings();

            for (Message warning : warnings) {
                System.out.println(warning.getMessage());
            }
        }

        if (messages.getErrors().size() > 0) {
            List<Error> errors = messages.getErrors();

            for (Error error : errors) {
                System.out.println(error.getMessage());
            }
            return false;
        } else {
            return true;
        }
    }

    private void buildGUI(Form ast, ValueData valueData) {
        GUI gui = new GUI (ast, new WidgetFactory(),
                new GUIManager(new FormFrame(ast.getIdentifier().getName())),
                valueData
        );
        gui.showUI();
    }
}
