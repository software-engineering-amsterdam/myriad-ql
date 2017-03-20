package qls;

import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.Faults;
import QL.QLLexer;
import QL.QLParser;
import QL.ReferenceTable;
import QL.ast.Form;
import QL.ast.type.Type;
import QL.ui.Environment;
import QL.ui.Questionnaire;
import qls.ast.Stylesheet;


public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "stylesheet taxOfficeExample "
				 + "page Housing { "
				 + "section \"Buying\" "
				 + "question hasBoughtHouse "
				 + "widget checkbox "
		 		 + "section \"Loaning\" "
				 + "question hasMaintLoan "
				 + "default boolean widget radio(\"Yes\", \"No\") "
				 + "}";
		
		ANTLRInputStream input = new ANTLRInputStream( tmp );

		qlsLexer lexer = new qlsLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		qlsParser parser = new qlsParser(tokens);
		Stylesheet stylesheet = parser.stylesheet().result;
		System.out.println(stylesheet);
		
		// TODO faults from ql?
		qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(ql());
		
		Faults faults = analyzer.analyze(stylesheet);
		
		Environment env = new Environment(ql());
		
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.main(createForm(), env, faults);

	}
	
	public static ReferenceTable ql() {
		
		Form form = createForm();

		QL.semantic.Analyzer analyzer = new QL.semantic.Analyzer();
		
		// TODO pass faults QL QLS
		ReferenceTable variables = analyzer.analyze(form);

		return variables;
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