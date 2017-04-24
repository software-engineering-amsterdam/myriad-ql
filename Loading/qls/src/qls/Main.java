package qls;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
		Stylesheet stylesheet = createStylesheet();
		Form form = createForm();

		ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
		ReferenceTable referenceTable = qlAnalyzer.analyze(form);
		
		List<Message> messages = qlAnalyzer.getMessages();
	
		qls.semantic.Analyzer analyzer = new Analyzer(referenceTable, messages);
		
		analyzer.analyze(stylesheet);

		PrettyQuestionnaire questionnaire = new PrettyQuestionnaire();
		questionnaire.main(form, new Environment(referenceTable), messages, stylesheet);
	}

	
	private static Form createForm() throws IOException {
		InputStream src = new FileInputStream("assets/questionnaire.ql");
		ANTLRInputStream input = new ANTLRInputStream(src);
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		return parser.form().result;
	}

	private static Stylesheet createStylesheet() throws IOException {
		InputStream src = new FileInputStream("assets/stylesheet.qls");
		ANTLRInputStream input = new ANTLRInputStream(src);

		qlsLexer lexer = new qlsLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		qlsParser parser = new qlsParser(tokens);
		return parser.stylesheet().result;
	}
	

}