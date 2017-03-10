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
import ql.semanticchecker.IdentifierChecker;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.Message;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.qlerrors.Error;
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

/**
 * Created by LGGX on 02-Mar-17.
 */
public class QLS {

    private final String inputFileQL;
    private final String inputFileQLS;

    private static final String ALLOWED_EXTENSION_QL = "ql";
    public static final String ALLOWED_EXTENSION_QLS = "qls";

    private Map<String, Type> identifierMap;

    private QLS(String inputFileQL, String inputFileQLS) throws IOException, IllegalArgumentException {
        this.inputFileQL = inputFileQL;
        this.inputFileQLS = inputFileQLS;
    }

    public static void main(String[] arguments) throws Exception {
        String inputFileQL = "./src/test.ql";
        String inputFileQLS = "./src/test.qls";
        qls.QLS qls = new qls.QLS(inputFileQL, inputFileQLS);
        qls.testFunctionality();
    }

    private void testFunctionality() throws IOException, IllegalArgumentException{
        if (!fileExists()) {
            throw new IOException();
        }

        if (!correctExtension()) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(inputFileQL);
        Form qlAST = getASTQL(qlInputStream);

        MessageData messages = new MessageData();

        Boolean semanticallyCorrectQL = checkSemanticCorrectnessQL(qlAST, messages);

        if(!semanticallyCorrectQL) {
            System.out.println("QL form is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }

        InputStream qlsInputStream = new FileInputStream(inputFileQLS);
        StyleSheet qlsAST = getASTQLS(qlsInputStream);

        Boolean semanticallyCorrectQLS = checkSemanticCorrectnessQLS(qlsAST, messages);

        if(!semanticallyCorrectQLS) {
            System.out.println("QLS form is semantically incorrect!");

            for (Error error : messages.getErrors()) {
                System.out.println(error.getMessage());
            }
            System.exit(1);
        }

        /*
        System.out.println("Create GUI...");
        Context questionStates =  new Context();
        buildGUI(qlAST, questionStates);
        */
    }

    private boolean fileExists() {
        Path pathQL = Paths.get(inputFileQL);
        Path pathQLS = Paths.get(inputFileQLS);
        return (Files.exists(pathQL) && Files.exists(pathQLS));
    }

    private boolean correctExtension() {
        String fileExtensionQL = inputFileQL.substring(inputFileQL.lastIndexOf(".") + 1, inputFileQL.length());
        String fileExtensionQLS = inputFileQLS.substring(inputFileQLS.lastIndexOf(".") + 1, inputFileQLS.length());
        return (fileExtensionQL.equals(ALLOWED_EXTENSION_QL) && fileExtensionQLS.equals(ALLOWED_EXTENSION_QLS));
    }

    private Form getASTQL(InputStream inputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor(parseTree);
        Node nodeAST = parseTree.accept(astVisitor);

        return (Form) nodeAST;
    }

    private StyleSheet getASTQLS(InputStream inputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLSLexer lexer = new QLSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokens);

        ParseTree parseTree = parser.stylesheet();
        QLSASTVisitor astVisitor = new QLSASTVisitor(parseTree);
        Node nodeAST = parseTree.accept(astVisitor);

        return (StyleSheet) nodeAST;
    }

    private boolean checkSemanticCorrectnessQL(Form qlAST, MessageData messages) {
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        new IdentifierChecker(qlAST, identifierToTypeMap, messages);
        new TypeChecker(qlAST, identifierToTypeMap, messages);

        this.identifierMap = identifierToTypeMap;

        if (messages.containsWarnings()) {
            for (Message warning : messages.getWarnings()) {
                System.out.println(warning.getMessage());
            }
        }

        return messages.containsErrors();
    }

    private boolean checkSemanticCorrectnessQLS(StyleSheet qlsAST, MessageData messages) {

        new QLSTypeChecker(messages, identifierMap, qlsAST);

        return messages.containsErrors();
    }

}
