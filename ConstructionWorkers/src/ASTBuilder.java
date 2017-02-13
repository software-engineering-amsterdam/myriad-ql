
import java.io.IOException;
import java.io.InputStream;

import ASTnodes.Form;
import ASTnodes.Node;
import antlr.QLLexer;
import antlr.QLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
/**
 * Created by LGGX on 11-Feb-17.
 */
public class ASTBuilder {

    private final QLParser parser;

    public ASTBuilder(InputStream inputStream) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        this.parser = new QLParser(tokens);
    }

    public Form buildForm() {
        return (Form) buildFromTree(parser.form());
    }

    private Node buildFromTree(ParseTree tree) {
        return tree.accept(new ASTVisitor(tree));
    }
}
