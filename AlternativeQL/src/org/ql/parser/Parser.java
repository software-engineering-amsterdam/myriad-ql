package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.*;
import org.ql.grammar.QLParserLexer;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserParser.*;
import org.ql.grammar.QLParserVisitor;

public class Parser {

    private final QLParserVisitor<Node> visitor = new Visitor();

    public Form parseForm(String code) {
        return (Form) visitor.visit(createParser(code).form());
    }

    public Statement parseStatement(String code) {
        return (Statement) visitor.visit(createParser(code).statement());
    }

    public Expression parseExpression(String code) {
        return (Expression) visitor.visit(createParser(code).expression());
    }

    private QLParserParser createParser(String code) {
        return new QLParserParser(new CommonTokenStream(new QLParserLexer(new ANTLRInputStream(code))));
    }
}
