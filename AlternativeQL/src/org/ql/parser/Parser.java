package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.Node;
import org.ql.ast.Visitor;
import org.ql.ast.form.Form;
import org.ql.grammar.QLParserLexer;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserParser.*;
import org.ql.grammar.QLParserVisitor;

public class Parser {

    private final QLParserVisitor<Node> visitor = new Visitor();

    public Form parse(String code) {
        return (Form) visitor.visit(parseFormContext(code));
    }

    private FormContext parseFormContext(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        return qlParser.form();
    }
}
