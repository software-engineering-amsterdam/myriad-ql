package qls;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import QLS.QLSLexer;

public class Main {
	public static void main(String[] args) throws Exception {
		String tmp = "stylesheet taxOfficeExample "
				 + "page Housing { "
				 + "section \"Buying\""
				 + "question hasBoughtHouse "
				 + "widget checkbox"
		 		 + "section \"Loaning\""
				 + "question hasMaintLoan"
				 + "}";
		
		ANTLRInputStream input = new ANTLRInputStream( tmp );

		QLSLexer lexer = new QLSLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokens);
		Form form = parser.form().result;
	}
}