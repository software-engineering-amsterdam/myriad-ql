/**
 * QLTestSetUp.java.
 */

package ql.unittests;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.astnodes.ASTVisitor;
import ql.astnodes.Form;

import org.junit.Before;
import ql.astnodes.Node;
import ql.astnodes.expressions.literals.Identifier;
import ql.gui.formenvironment.ValueData;
import ql.semanticchecker.IdentifierChecker;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.MessageData;

import java.io.*;
import java.util.HashMap;

public abstract class QLTestSetUp {

    protected Form form;
    protected ValueData questionStates;
    protected IdentifierChecker idChecker;
    protected TypeChecker typeChecker;

    protected String inputFileName;
    protected String inputFilePath;

    private final String path = "./src/ql/unittests/qltestforms/";

    @Before
    public void setUp() throws IOException {

        if (inputFileName == null) {
            System.err.println("Undefined filename.");
            System.exit(-1);
        }

        inputFilePath = path.concat(inputFileName);

        InputStream qlInputStream = new FileInputStream(inputFilePath);

        ANTLRInputStream input = new ANTLRInputStream(qlInputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor(parseTree);
        Node nodeAST = parseTree.accept(astVisitor);

        form = (Form) nodeAST;
        questionStates =  new ValueData();

        MessageData messages = new MessageData();
        HashMap identifierToTypeMap = new HashMap<>();

        idChecker = new IdentifierChecker(form, identifierToTypeMap, messages);
        typeChecker= new TypeChecker(form, identifierToTypeMap, messages);

    }
}
