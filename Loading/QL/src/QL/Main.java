package QL;

import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QL.ast.Form;
import QL.semantic.Analyzer;
import QL.ui.Questionnaire;
import QL.ui.Environment;

import java.io.FileInputStream;
import java.io.InputStream;

class Main {
	public static void main(String[] args) throws Exception {
		InputStream src = new FileInputStream("assets/questionnaire.ql");
		ANTLRInputStream input = new ANTLRInputStream(src);
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