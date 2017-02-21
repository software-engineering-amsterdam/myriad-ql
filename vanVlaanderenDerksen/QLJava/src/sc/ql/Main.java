package sc.ql;

import sc.ql.antlr.*;
import sc.ql.ast.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream("samples/sample-ql2.frm");
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.form();
        
        AstVisitor visitor = new AstVisitor();
        visitor.visit(tree);
    }
	
}