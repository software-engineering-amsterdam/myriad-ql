package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.grammar.QLParserLexer;
import org.ql.grammar.QLParserParser;

public class Parser {
    public Form parse(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.FormContext formContext = qlParser.form();
        return new Form(new Identifier(formContext.ID().getText()));
    }
}
