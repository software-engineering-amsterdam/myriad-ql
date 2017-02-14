package sc.ql;

import sc.ql.antlr.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream("samples/sample-ql.frm");
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        
        System.out.println(parser.form());
    }
	
}