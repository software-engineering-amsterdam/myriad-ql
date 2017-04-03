package qls;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ql.QLLexer;
import ql.QLParser;
import ql.ReferenceTable;
import ql.ast.Form;
import ql.message.Message;
import ql.ui.Environment;
import qls.ast.Stylesheet;
import qls.semantic.Analyzer;
import qls.ui.PrettyQuestionnaire;


class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "stylesheet taxOfficeExample \n"
				 + "page Housing { \n"
				 + "default integer { "
				 + "width: 400 "
				 + "font: \"Arial\" "
				 + "fontsize: 14 "
				 + "color: \"#000000\" "
				 + "widget spinbox"
				 + "} "
		 		 + "default boolean widget checkbox \n"
		 		 + "default integer widget spinbox \n"
				 + "section \"Buying\" \n"
				 + "default boolean widget dropdown(\"Yes\", \"No\") \n"
				 + "question Name0 \n"
		 		 + "section \"Loaning\" \n"
				 + "question Name2 \n"
				 + "question Name1 widget checkbox\n"
				 + "}";
		
		ANTLRInputStream input = new ANTLRInputStream( tmp );

		qlsLexer lexer = new qlsLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		qlsParser parser = new qlsParser(tokens);
		Stylesheet stylesheet = parser.stylesheet().result;
		
		Form form = createForm();

		ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
		ReferenceTable referenceTable = qlAnalyzer.analyze(form);
		
		List<Message> messages = qlAnalyzer.getMessages();
	
		qls.semantic.Analyzer analyzer = new Analyzer(referenceTable, messages);
		
		analyzer.analyze(stylesheet);

		PrettyQuestionnaire questionnaire = new PrettyQuestionnaire();
		questionnaire.main(form, new Environment(referenceTable), messages, stylesheet);
	}

	
	private static Form createForm() {
		String tmp = "form Testing { "
				 + "Name0: \"How much did your house cost?\" boolean "
				 + "Name1: \"You have to pay\" boolean"
				 + "}";

		ANTLRInputStream input = new ANTLRInputStream( tmp );

		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokens);
		return parser.form().result;
	}
	

}