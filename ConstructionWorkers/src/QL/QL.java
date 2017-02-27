package QL; /**
 * QL.QL.java.
 */

import QL.ASTnodes.ASTBuilder;
import QL.ASTnodes.Form;
import QL.GUI.GUI;
import QL.GUI.GUIComponents.FormFrame;
import QL.GUI.GUIComponents.GUIManager;
import QL.GUI.GUIComponents.GUIWidgets.WidgetFactory;
import QL.semanticChecker.SemanticChecker;
import QL.semanticChecker.formDataStorage.valueData.ValueData;
import QL.semanticChecker.messageHandling.MessageData;
import QL.semanticChecker.messageHandling.MessageHandler;
import QL.semanticChecker.messageHandling.errors.ErrorHandler;
import QL.semanticChecker.messageHandling.warnings.WarningHandler;

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
            System.out.println("QL.QL form is semantically incorrect.");
            System.exit(0);
        } else {
            System.out.println("Create QL.GUI...");
            buildGUI(qlAST, questionStates);
            //System.exit(0);
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
            List<WarningHandler> warnings = messages.getWarnings();

            for (MessageHandler warning : warnings) {
                System.out.println(warning.getMessage());
            }
        }

        if (messages.getErrors().size() > 0) {
            List<ErrorHandler> errors = messages.getErrors();

            for (ErrorHandler error : errors) {
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
