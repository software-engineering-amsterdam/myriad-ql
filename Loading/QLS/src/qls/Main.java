package qls;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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
	}
}