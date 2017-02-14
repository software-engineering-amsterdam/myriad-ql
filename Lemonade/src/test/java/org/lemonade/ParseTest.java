package org.lemonade;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseTest {

    private String simpleForm;

    @Before
    public void setUp() throws Exception {
        simpleForm = "form { test : \"test string\" boolean }";
    }

    @Test
    public void testExploratoryString() throws IOException {

        String simpleForm = "form name {testquest : \"questionfield?\" currency}";

        CharStream inputCharStream = new ANTLRInputStream(new StringReader(simpleForm));
        TokenSource tokenSource = new QLLexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        QLParser parser = new QLParser(inputTokenStream);

        QLParser.FormContext context = parser.form();
    }

    @Test
    public void testWalk() throws IOException {
        String simpleForm = "form naam {tmp : \"echt?\" boolean}\u001a";
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm));

        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();

        QLLoader loader = new QLLoader();

    }

    public void happyCase() throws Exception {
        TestErrorListener errorListener = new TestErrorListener();
        QLParser.FormContext context = parseForm(simpleForm, errorListener);

        assertThat(errorListener.isFailed()).isFalse();

        assertThat(context.getChild(0).getText()).isEqualTo("form");
        assertThat(context.getChild(1).getText()).isEqualTo("{");
        assertThat(context.getChild(2).getText()).isEqualTo("test:\"test string\"boolean");
        assertThat(context.getChild(3).getText()).isEqualTo("}");
    }

    private QLParser.FormContext parseForm(String form, TestErrorListener errorListener) throws Exception {
        CharStream inputCharStream = new ANTLRInputStream(new StringReader(form));
        TokenSource tokenSource = new QLLexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        QLParser parser = new QLParser(inputTokenStream);

        parser.addErrorListener(errorListener);

        return parser.form();
    }

    class TestErrorListener implements ANTLRErrorListener {

        private boolean failed = false;

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(final boolean failed) {
            this.failed = failed;
        }

        @Override public void syntaxError(
                final Recognizer<?, ?> recognizer,
                final Object o,
                final int i,
                final int i1,
                final String s,
                final RecognitionException e) {
            setFailed(true);
        }

        @Override public void reportAmbiguity(
                final Parser parser,
                final DFA dfa,
                final int i,
                final int i1,
                final boolean b,
                final BitSet bitSet,
                final ATNConfigSet atnConfigSet) {
            setFailed(true);
        }

        @Override public void reportAttemptingFullContext(
                final Parser parser,
                final DFA dfa,
                final int i,
                final int i1,
                final BitSet bitSet,
                final ATNConfigSet atnConfigSet) {
            setFailed(true);
        }

        @Override public void reportContextSensitivity(
                final Parser parser,
                final DFA dfa,
                final int i,
                final int i1,
                final int i2,
                final ATNConfigSet atnConfigSet) {
            setFailed(true);
        }
    }
//
//    @Test
//    public void printParse() throws IOException {
//        String simpleForm = "form bla {tmp : \"ja?\" boolean if (True) { tmpNest : \"nee?\" boolean}}";
//        // create a CharStream that reads from standard input
//        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm));
//        // create a lexer that feeds off of input CharStream
//        QLLexer lexer = new QLLexer(input);
//        // create a buffer of tokens pulled from the lexer
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        // create a parser that feeds off the tokens buffer
//        QLParser parser = new QLParser(tokens);
//        ParseTree tree = parser.form(); // begin parsing at form rule
//        System.out.println(tree.toStringTree(parser));
//    }
}

