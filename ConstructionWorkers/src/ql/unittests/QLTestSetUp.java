/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/unittests/QLTestSetUp.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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
import ql.astnodes.types.Type;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.MessageData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class QLTestSetUp {

    protected Form form;
    TypeChecker typeChecker;
    String inputFileName;

    @Before
    public void setUp() throws IOException {

        if (inputFileName == null) {
            System.err.println("Undefined filename.");
            System.exit(-1);
        }

        String PATH = "./src/ql/unittests/";
        String inputFilePath = PATH.concat(inputFileName);

        InputStream qlInputStream = new FileInputStream(inputFilePath);

        ANTLRInputStream input = new ANTLRInputStream(qlInputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        form = (Form) nodeAST;

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        typeChecker= new TypeChecker(form, identifierToTypeMap, messages);
    }
}
