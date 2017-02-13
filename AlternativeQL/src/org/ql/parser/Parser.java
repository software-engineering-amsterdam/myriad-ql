package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.form.Form;
import org.ql.grammar.QLParserLexer;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserParser.*;

public class Parser {

    private final ASTFactory astFactory = new ASTFactory();

    public Form parse(String code) {
        return astFactory.createAST(parseFormContext(code));
    }

    private FormContext parseFormContext(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        return qlParser.form();
    }
}
