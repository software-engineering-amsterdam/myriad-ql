import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ast.Form;
import ast.Visitor;

public class Main {
	public static void main(String[] args) throws Exception {

// 		 String tmp = "form Testing { Name1: \"Question\" boolean\n"
// 		 		+ "Name2: \"Question\" boolean"
		 String tmp = "form Testing { Name0: \"Question\" boolean\n"
		 		+ "Name1: \"Question\" boolean\n"
		 		+ "if (22.12.2016 == \"text\") {"
		 		+ "Name2: \"Question\" boolean\n"
		 		+ " }"
				+ "Name3: \"Question\" boolean\n"
				+ "if (3.232 + 1) {"
				+ "Name4: \"Question\" boolean\n"
				+ "}"
		 		+ " }";
		
		 ANTLRInputStream input = new ANTLRInputStream( tmp );
		
		 QLLexer lexer = new QLLexer(input);
		
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		 QLParser parser = new QLParser(tokens);
		 // System.out.println(parser.form().result.getBlock().getStatements().get(0).getExpression().print());
		 Form form = parser.form().result;
		 Environment environment = new Environment();
		 EvalVisitor ASTVisitor = new EvalVisitor(environment);
		 ASTVisitor.visit(form);
//		 System.out.println(parser.root().result.getBlock().getQuestions());
		// System.out.println(parser.root().result.getBlock().getStatements().get(0).getExpression().isEval());
		 // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}