package QL;

import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.ast.Form;
import QL.semantic.Analyzer;
import QL.ui.Questionnaire;
import QL.ui.Environment;

public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "form Testing { "
				 + "Name0: \"Question0\" integer "
				 + "Name1: \"Question1\" boolean "
				 + "if (Name0 < 5) {"
				 + "if (Name0 == 4) {"
 		 		 + "Name2: \"Question2\" boolean"
				 + "} else { "
				 + "Name9: \"Question9\" boolean } } "
				 + "Name3: \"Question3\" string "
				 + "}";

		ANTLRInputStream input = new ANTLRInputStream( tmp );

		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokens);
		Form form = parser.form().result;

		Analyzer analyzer = new Analyzer();
		ReferenceTable referenceTable = analyzer.analyze(form);
		
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.main(form, new Environment(referenceTable), analyzer.getMessages());
	}
	
	
}