package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.form.Form;
import org.ql.ast.Identifier;
import org.ql.ast.declaration.Question;
import org.ql.ast.literal.BooleanLiteral;
import org.ql.ast.literal.FloatLiteral;
import org.ql.ast.literal.IntegerLiteral;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;
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

    public Question parseQuestion(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.QuestionContext questionContext = qlParser.question();

        return new Question(new Identifier(questionContext.ID().getText()),
                questionContext.STRING().getText(),
                new Type(questionContext.type().getText()));
    }

    public BooleanLiteral parseBooleanLiteral(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.Boolean_literalContext booleanLiteralContext = qlParser.boolean_literal();

        return new BooleanLiteral(Boolean.parseBoolean(booleanLiteralContext.getText()));
    }

    public IntegerLiteral parseIntegerLiteral(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.Integer_literalContext integerLiteralContext = qlParser.integer_literal();

        return new IntegerLiteral(Integer.parseInt(integerLiteralContext.INTEGER().getText()));
    }

    public FloatLiteral parseFloatLiteral(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.Float_literalContext floatLiteralContext = qlParser.float_literal();

        return new FloatLiteral(Float.parseFloat(floatLiteralContext.FLOAT().getText()));
    }

    public StringLiteral parseStringLiteral(String code) {
        QLParserLexer lexer = new QLParserLexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParserParser qlParser = new QLParserParser(tokens);
        QLParserParser.String_literalContext stringLiteralContext = qlParser.string_literal();

        return new StringLiteral(stringLiteralContext.STRING().getText());
    }
}
