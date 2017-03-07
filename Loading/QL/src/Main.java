import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.Warning;
import ast.Form;
import semantic.TypeChecker;
import ui.Questionnaire;

public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "form Testing { "
				 + "Name0: \"Question0\" integer "
				 + "Name1: \"Question1\" integer (Name0 + 2)"
				 + "if (Name0 < 5) {"
				 + "if (Name0 == 4) {"
 		 		 + "Name2: \"Question2\" boolean"
				 + "} else { "
				 + "Name9: \"Question9\" boolean } } "
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
		Form form = parser.form().result;

		System.out.println("----");

		TypeChecker typeChecker = new TypeChecker();

		List<Warning> warnings = typeChecker.analyze(form);
		
		
		evaluation.Environment env = new evaluation.Environment(typeChecker.getVariableTypes());
		
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.main(form, env, warnings);

		System.out.println("LINE NUMBER: " + form.getLine());

	}
}