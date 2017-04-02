package qls;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.QLLexer;
import QL.QLParser;
import QL.ReferenceTable;
import QL.ast.Form;
import QL.message.Message;
import QL.ui.Environment;
import qls.ast.Stylesheet;
import qls.semantic.Analyzer;
import qls.ui.PrettyQuestionnaire;


class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "stylesheet taxOfficeExample \n"
				 + "page Housing { \n"
		 		 + "default boolean widget checkbox \n"
		 		 + "default integer widget spinbox \n"
				 + "section \"Buying\" \n"
				 + "default boolean widget radio(\"Yes\", \"No\") \n"
				 + "question Name0 widget numberfield \n"
		 		 + "section \"Loaning\" \n"
				 + "question Name1 widget numberfield \n"
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
	
		qls.semantic.Analyzer analyzer = new Analyzer(referenceTable, messages);
		
		analyzer.analyze(stylesheet);

		PrettyQuestionnaire questionnaire = new PrettyQuestionnaire();
		questionnaire.main(form, new Environment(referenceTable), messages, stylesheet);
	}
	

	
	private static Form createForm() {
		String tmp = "form Testing { "
				 + "Name0: \"How much did your house cost?\" integer "
				 + "Name1: \"You have to pay\" integer (Name0 + 2)"
				 + "}";

		ANTLRInputStream input = new ANTLRInputStream( tmp );

		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokens);
		return parser.form().result;
	}
	

}