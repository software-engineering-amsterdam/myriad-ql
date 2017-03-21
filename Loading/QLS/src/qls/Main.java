package qls;

import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.QLLexer;
import QL.QLParser;
import QL.ReferenceTable;
import QL.ast.Form;
import QL.message.Message;
import QL.ui.Environment;
import QL.ui.Questionnaire;
import QL.ui.Style;
import QL.ui.StyleTable;
import qls.ast.Stylesheet;
import qls.semantic.Analyzer;


public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "stylesheet taxOfficeExample \n"
				 + "page Housing { \n"
				 + "section \"Buying\" \n"
				 + "question Name0 \n"
				 + "widget checkbox \n"
		 		 + "section \"Loaning\" \n"
				 + "question Name1 \n"
		 		 + "default boolean widget checkbox"
				 // + "default boolean widget radio(\"Yes\", \"No\") \n"
				 + "}";
		
		ANTLRInputStream input = new ANTLRInputStream( tmp );

		qlsLexer lexer = new qlsLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		qlsParser parser = new qlsParser(tokens);
		Stylesheet stylesheet = parser.stylesheet().result;
		System.out.println(stylesheet);
		
		Form form = createForm();

		QL.semantic.Analyzer qlAnalyzer = new QL.semantic.Analyzer();		
		ReferenceTable referenceTable = qlAnalyzer.analyze(form);
		
		List<Message> messages = qlAnalyzer.getMessages();
	
		qls.semantic.Analyzer analyzer = new Analyzer(referenceTable);
		
		StyleTable styleTable = analyzer.analyze(stylesheet);
		
		messages.addAll(analyzer.getMessages());
		
		// TODO
		// Evaluate the ast to create a variablename - Style map
		// Rename DefaultStyle to Style?
		// extend the referenceTable with the StyleTable
		

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.main(form, new Environment(referenceTable, styleTable), messages);
	}
	

	
	public static Form createForm() {
		String tmp = "form Testing { "
				 + "Name0: \"Question0\" integer "
				 + "Name1: \"Question1\" integer (Name0 + 2)"
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
		return parser.form().result;
	}
	

}