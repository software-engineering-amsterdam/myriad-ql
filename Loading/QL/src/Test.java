import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        // create a lexer that feeds off of input CharStream
        HelloLexer lexer = new HelloLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        HelloParser parser = new HelloParser(tokens);

        ParseTree tree = parser.r();    // begin parsing at rule r
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}