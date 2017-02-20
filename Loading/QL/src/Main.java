import java.util.Map;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ast.Form;
import ast.Visitor;
import ast.atom.Atom;
import ui.UIFactory;

public class Main {
	public static void main(String[] args) throws Exception {

// 		 String tmp = "form Testing { Name1: \"Question\" boolean\n"
// 		 		+ "Name2: \"Question\" boolean"
		 String tmp = "form Testing { Name0: \"Question\" boolean\n"
		 		+ "Name1: \"Question1\" boolean\n"
		 		+ "if (\"text1\" == \"text2\") {"
		 		+ "Name2: \"Question2\" boolean\n"
		 		+ " }"
				+ "Name3: \"Question3\" boolean\n"
				+ "if (11 == 3) {"
				+ "Name4: \"Question4\" boolean\n"
				+ "} "
				+ "if (true && true) {"
				+ "Name5: \"Question5\" boolean\n"
				+ "}"
		 		+ " }";
		
		 ANTLRInputStream input = new ANTLRInputStream( tmp );
		
		 QLLexer lexer = new QLLexer(input);
		
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		 QLParser parser = new QLParser(tokens);
		 // System.out.println(parser.form().result.getBlock().getStatements().get(0).getExpression().print());
		 Form form = parser.form().result;

		 System.out.println("----");

		 Environment environment = new Environment();
		 EvalVisitor ASTVisitor = new EvalVisitor(environment);
		 ASTVisitor.visit(form);

		 System.out.println("----");
		 
		 QuestionVisitor QVisitor = new QuestionVisitor(environment);
		 QVisitor.visit(form);
		 Map<String, Atom> answers = QVisitor.getEnvironment().getAnswers();
		 
		 for (String answer : answers.keySet()) {
			 System.out.println("Question: " + answer);
		 }
		 
//		 System.out.println(parser.root().result.getBlock().getQuestions());
		// System.out.println(parser.root().result.getBlock().getStatements().get(0).getExpression().isEval());
		 // System.out.println(tree.toStringTree(parser)); // print LISP-style tree

		UIFactory.main();
	}
}