import java.util.Map;

import ast.type.Type;
import evaluation.Environment;
import evaluation.Evaluator;
import semantic.TypeChecker;

import org.antlr.v4.runtime.*;

import ast.Form;
import ast.atom.Atom;
import ui.Questionnaire;

public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "form Testing { "
				 + "Name1: \"Question1\" boolean "
				 + "if (Name1) {"
 		 		 + "Name2: \"Question2\" boolean"
				 + "}"
				 + "Name3: \"Question3\" boolean "
				 + "}";
//		String tmp = "form Testing { Name0: \"Question\" boolean\n"
//				+ "Name1: \"Question1\" integer\n"
//				+ "if (((1 + 5) * 3) == ((15+2) + 1)) {"
//				+ "Name2: \"Question2\" boolean\n"
//				+ " }"
//				+ "Name31: \"Question31\" string\n"
//				+ "Name32: \"Question32\" integer (1 + 2)\n"
//				+ "if (Name5 == ((Name32) != Name31)) {"
//				+ "Name4: \"Question4\" boolean\n"
//				+ "} else if (1 == 2) {"
//				+ "Name8: \"Question8\" integer\n"
//				+ "} else {"
//				+ "Name9: \"Question9\" integer\n"
//				+ "}"
//				+ "if ((true && true) || false && (true)) {"
//				+ "Name5: \"Question5\" boolean\n"
//				+ "} else {"
//				+ "Name99: \"Question99\" string}"
//				+ " }";

		ANTLRInputStream input = new ANTLRInputStream( tmp );

		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokens);
		// System.out.println(parser.form().result.getBlock().getStatements().get(0).getExpression().print());
		Form form = parser.form().result;

		System.out.println("----");

		TypeChecker typeChecker = new TypeChecker();

		semantic.Environment semanticEv = typeChecker.analyze(form);

		Environment env = new Environment();
//		Evaluator evaluator = new Evaluator(env);
		
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.main(form);

		System.out.println("LINE NUMBER: " + form.getLine());

	}
}