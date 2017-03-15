package qls;

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
import ql.gui.formenvironment.Context;
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

public class QLS {

    public static void main(String[] arguments) throws Exception {
        String inputFileQL = "./src/test.ql";
        String inputFileQLS = "./src/test.qls";

        new QLS(inputFileQL, inputFileQLS);
    }

    private QLS(String inputQL, String inputQLS) throws IOException {

        if (!fileExists(inputQL, inputQLS)) {
            throw new IOException();
        }

        InputStream qlInputStream = new FileInputStream(inputQL);
        Form qlAST = getASTQL(qlInputStream);

        Map<String, Type> identifierMap = new HashMap<>();
        MessageData messages = new MessageData();

        Boolean semanticallyCorrectQL = checkSemanticCorrectnessQL(qlAST, messages, identifierMap);

        if(!semanticallyCorrectQL) {
            System.out.println("QL form is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }

        InputStream qlsInputStream = new FileInputStream(inputQLS);
        StyleSheet qlsAST = getASTQLS(qlsInputStream);

        Boolean semanticallyCorrectQLS = checkSemanticCorrectnessQLS(qlsAST, messages, identifierMap);

        if(!semanticallyCorrectQLS) {
            System.out.println("QLS form is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }


        System.out.println("Create GUI...");
        Context questionStates =  new Context();
        buildGUI(qlAST, questionStates);

    }

    private boolean fileExists(String inputQL, String inputQLS) {
        Path pathQL = Paths.get(inputQL);
        Path pathQLS = Paths.get(inputQLS);
        return (Files.exists(pathQL) && Files.exists(pathQLS));
    }

    private Form getASTQL(InputStream inputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        return (Form) nodeAST;
    }

    private StyleSheet getASTQLS(InputStream inputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLSLexer lexer = new QLSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokens);

        ParseTree parseTree = parser.stylesheet();
        QLSASTVisitor astVisitor = new QLSASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        return (StyleSheet) nodeAST;
    }

    private boolean checkSemanticCorrectnessQL(Form qlAST, MessageData messages,
                                               Map<String, Type> identifierMap) {

        new IdentifierChecker(qlAST, identifierMap, messages);
        new TypeChecker(qlAST, identifierMap, messages);

        if (messages.containsNoWarnings()) {
            for (Message warning : messages.getWarnings()) {
                System.out.println(warning.getMessage());
            }
        }

        return messages.containsNoErrors();
    }

    private boolean checkSemanticCorrectnessQLS(StyleSheet qlsAST, MessageData messages,
                                                Map<String, Type> identifierMap) {

        new QLSTypeChecker(messages, identifierMap, qlsAST);

        return messages.containsNoErrors();
    }

    private void buildGUI(Form ast, Context context) {
        GUI gui = new GUI (ast, context);
        gui.showGUI();
    }
}
