package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.ql.ast.Form;
import org.ql.grammar.QLParserLexer;
import org.ql.grammar.QLParserParser;

import java.io.File;

public class Parser {
    public Form parse(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        ParseTree formContext = qlParser.form();
        return new Form();
    }
}
