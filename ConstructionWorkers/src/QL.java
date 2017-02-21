/**
 * QL.java.
 *
 * Actually a test class...
 */

import ASTnodes.Form;
import lexicalChecker.LexicalChecker;
import lexicalChecker.messageHandling.MessageData;
import lexicalChecker.messageHandling.MessageHandler;
import lexicalChecker.messageHandling.errors.ErrorHandler;
import lexicalChecker.messageHandling.warnings.WarningHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class QL {

    private static final String ALLOWED_EXTENSION = "ql";

    private QL(String fileName) throws IOException, IllegalArgumentException {
        if (!fileExists(fileName)) {
            throw new IOException();
        }

        if (!this.correctExtension(fileName)) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(fileName);
        Form qlAST = this.getAST(qlInputStream);
        LexicalChecker lexicalChecker = new LexicalChecker(qlAST);

        MessageData messages = lexicalChecker.getMessages();

        if (messages.getWarnings().size() > 0) {
            List<WarningHandler> warnings = messages.getWarnings();
            for (MessageHandler warning : warnings) {
                System.out.println(warning.getMessage());
            }
        }

        // Print error messages...
        if (messages.getErrors().size() > 0) {
            List<ErrorHandler> errors = messages.getErrors();

            for (ErrorHandler error : errors) {
                System.out.println(error.getMessage());
            }
        }
    }

    public static void main(String[] arguments) throws Exception {
        run();
    }

    private static void run() throws IOException, IllegalArgumentException {
        new QL("./src/test.ql");
    }

    private boolean fileExists(String fileName) {
        Path path = Paths.get(fileName);
        return Files.exists(path);
    }

    private boolean correctExtension(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        return fileExtension.equals(ALLOWED_EXTENSION);
    }

    private Form getAST(InputStream inputStream) throws IOException {
        ASTBuilder qlAstBuilder = new ASTBuilder(inputStream);
        return qlAstBuilder.buildAST();
    }

//    private boolean checkLexicalIntegrity(Form qlAST) {
//        LexicalChecker lexicalChecker = new LexicalChecker(qlAST);
//        MessageData messages = lexicalChecker.getMessages();
//
//        if (messages.getWarnings().size() > 0) {
//            List<WarningHandler> warnings = messages.getWarnings();
//            for (MessageHandler warning : warnings) {
//                System.out.println(warning.getMessage());
//            }
//        }
//
//        // Print error messages...
//        if (messages.getErrors().size() > 0) {
//            List<ErrorHandler> errors = messages.getErrors();
//
//            for (ErrorHandler error : errors) {
//                System.out.println(error.getMessage());
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
}
