/**
 * QL.java.
 */

import ASTnodes.Form;
import semanticChecker.SemanticChecker;
import semanticChecker.dependency.stateData.QuestionStateData;
import semanticChecker.messageHandling.MessageData;
import semanticChecker.messageHandling.MessageHandler;
import semanticChecker.messageHandling.errors.ErrorHandler;
import semanticChecker.messageHandling.warnings.WarningHandler;

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
        Boolean semanticallyCorrect = checkSemanticCorrectness(qlAST);

        if(!semanticallyCorrect) {
            System.out.println("QL form is semantically incorrect.");
            System.exit(0);
        } else {
            System.out.println("Create GUI...");
            System.exit(0);
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

    private boolean checkSemanticCorrectness(Form qlAST) {
        QuestionStateData questionStates =  new QuestionStateData();
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
}
