/**
 * ASTBuilder.java.
 */

package ASTnodes;

import java.io.IOException;
import java.io.InputStream;

import antlr.QLLexer;
import antlr.QLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ASTBuilder {

    private final QLParser parser;

    public ASTBuilder(InputStream inputStream) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new QLParser(tokens);
    }

    public Form buildAST() {
        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor(parseTree);
        Node nodeAST = parseTree.accept(astVisitor);
        return (Form) nodeAST;
    }
}
