/*
 * Software Construction - University of Amsterdam
 *
 * ./src/QLMain.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

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
import ql.semanticchecker.IdentifierChecker;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.Message;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.Error;
import qls.antlr.QLSLexer;
import qls.antlr.QLSParser;
import qls.astnodes.QLSASTVisitor;
import qls.astnodes.StyleSheet;
import qls.semanticchecker.QLSTypeChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QLMain {

    public static void main(String[] arguments) throws Exception {
        String inputFileQL = "./src/form.ql";
        String inputFileQLS = "./src/stylesheet.qls";

        new QLMain(inputFileQL, inputFileQLS);
    }

    private QLMain(String inputFileQL, String inputFileQLS) throws IOException {

        if (!fileExists(inputFileQL, inputFileQLS)) {
            throw new IOException();
        }

        InputStream qlInputStream = new FileInputStream(inputFileQL);
        Form qlAST = getASTQL(qlInputStream);

        Map<String, Type> identifierToTypeMap = new HashMap<>();
        MessageData messages = new MessageData();

        Boolean semanticallyCorrectQL = checkSemanticCorrectnessQL(qlAST, messages, identifierToTypeMap);

        if(!semanticallyCorrectQL) {
            System.out.println("QL form is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }

        InputStream qlsInputStream = new FileInputStream(inputFileQLS);
        StyleSheet qlsAST = getASTQLS(qlsInputStream);

        Boolean semanticallyCorrectQLS = checkSemanticCorrectnessQLS(qlsAST, messages, identifierToTypeMap);

        if(!semanticallyCorrectQLS) {
            System.out.println("QLS stylesheet is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }

        System.out.println("Create GUI...");
        buildGUI(qlAST);
    }

    private boolean fileExists(String inputFileQL, String inputFileQLS) {
        Path pathQL = Paths.get(inputFileQL);
        Path pathQLS = Paths.get(inputFileQLS);
        return (Files.exists(pathQL) && Files.exists(pathQLS));
    }

    private Form getASTQL(InputStream qlInputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(qlInputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        return (Form) nodeAST;
    }

    private StyleSheet getASTQLS(InputStream qlsInputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(qlsInputStream);
        QLSLexer lexer = new QLSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokens);

        ParseTree parseTree = parser.stylesheet();
        QLSASTVisitor astVisitor = new QLSASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        return (StyleSheet) nodeAST;
    }

    private boolean checkSemanticCorrectnessQL(Form qlAST, MessageData messages, Map<String, Type> identifierToTypeMap) {
        new IdentifierChecker(qlAST, identifierToTypeMap, messages);
        new TypeChecker(qlAST, identifierToTypeMap, messages);

        if (!messages.containsNoWarnings()) {
            for (Message warning : messages.getWarnings()) {
                System.out.println(warning.getMessage());
            }
        }

        return messages.containsNoErrors();
    }

    private boolean checkSemanticCorrectnessQLS(StyleSheet qlsAST, MessageData messages, Map<String, Type> identifierToTypeMap) {
        new QLSTypeChecker(messages, identifierToTypeMap, qlsAST);
        return messages.containsNoErrors();
    }

    private void buildGUI(Form ast) {
        GUI gui = new GUI (ast);
        gui.showGUI();
    }
}
