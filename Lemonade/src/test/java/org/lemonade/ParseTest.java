package org.lemonade;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.TypeCheckVisitor;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseTest {

    private String simpleForm;
    private String simpleForm2;
    private FormVisitor visitor = new FormVisitor();

    @Before
    public void setUp() throws Exception {

        simpleForm = "form name {" +
                "tmp1: \"Hoe groot is jouw decimal?\" decimal " +
                "if(((-2) + 4.0) * 8.0 >= tmp1) {tmp: \"yu\" money}}";

        simpleForm2 = "form Blader {\n"
                + "    test : \"Doet dit werken?\" boolean\n"
                + "    if (test == true) {\n"
                + "        testNest : \"Blijkbaar?\" boolean\n"
                + "    }\n"
                + "    testUnnest : \"Unnest?\" boolean\n"
                + "    testString : \"Typ eens wat\" string\n"
                + " }\n";

    }

    @Test
    public void astTest() throws IOException{
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm2));
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        QLParserErrorListener errorListener = new QLParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ParseTree tree = parser.form();

        if (errorListener.hasErrors()){
            System.err.printf("%s\n", errorListener);
            throw new IllegalStateException();//TODO fix error type.
        }

        Form root = (Form) tree.accept(visitor);
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        EvaluateVisitor evaluateVisitor = new EvaluateVisitor();
        root.accept(typeCheckVisitor);
        root.accept(evaluateVisitor);
    }
}

