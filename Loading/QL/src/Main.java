import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
	public static void main(String[] args) throws Exception {

		 String tmp = "form Testing { \"Name1 Question boolean\""
		 		+ "\"Name2 Question boolean\""
		 		+ "if (\"text\" == \"text\") {"
		 		+ "\"Name3 Question boolean\""
		 		+ " }"
				+ "\"Name4 Question boolean\""
		 		+ " }";
		
		 ANTLRInputStream input = new ANTLRInputStream( tmp );
		
		 QLLexer lexer = new QLLexer(input);
		
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		 QLParser parser = new QLParser(tokens);
		 // ParseTree tree = parser.root(); // begin parsing at rule 'root'
//		 System.out.println(parser.root().result.getBlock().getQuestions());
		 System.out.println(parser.root().result.getBlock().getStatements().get(0).getExpression().isEval());
		 // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}