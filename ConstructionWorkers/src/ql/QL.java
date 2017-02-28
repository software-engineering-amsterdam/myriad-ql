/**
 * QL.java.
 */

package ql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.astnodes.ASTVisitor;
import ql.astnodes.Form;
import ql.astnodes.Node;
import ql.astnodes.types.Type;
import ql.gui.GUI;
import ql.gui.components.FormFrame;
import ql.gui.components.GUIManager;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.semanticchecker.IdentifierChecker;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.Message;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.Error;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QL {

    private final String inputFile;

    private static final String ALLOWED_EXTENSION = "ql";

    private QL(String inputFile) throws IOException, IllegalArgumentException {
        this.inputFile = inputFile;
    }

    public static void main(String[] arguments) throws Exception {
        String inputFile = "./src/test.ql";
        QL ql = new QL(inputFile);
        ql.testFunctionality();
    }

    private void testFunctionality() throws IOException, IllegalArgumentException{
        if (!fileExists()) {
            throw new IOException();
        }

        if (!correctExtension()) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(inputFile);
        Form qlAST = getAST(qlInputStream);

        MessageData messages = new MessageData();

        Boolean semanticallyCorrect = checkSemanticCorrectness(qlAST, messages);

        if(!semanticallyCorrect) {
            System.out.println("QL form is semantically incorrect.");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }

            System.exit(1);
        } else {
            System.out.println("Create ql.gui...");
            Context questionStates =  new Context();
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
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor(parseTree);
        Node nodeAST = parseTree.accept(astVisitor);

        return (Form) nodeAST;
    }

    private boolean checkSemanticCorrectness(Form qlAST, MessageData messages) {
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        new IdentifierChecker(qlAST, identifierToTypeMap, messages);
        new TypeChecker(qlAST, identifierToTypeMap, messages);

        if (messages.containsWarnings()) {
            for (Message warning : messages.getWarnings()) {
                System.out.println(warning.getMessage());
            }
        }

        return messages.containsErrors();
    }

    private void buildGUI(Form ast, Context context) {
        GUI gui = new GUI (ast, new WidgetFactory(), new GUIManager(new FormFrame(ast.getIdentifier().getName())),
                context);
        gui.showUI();
    }
}
