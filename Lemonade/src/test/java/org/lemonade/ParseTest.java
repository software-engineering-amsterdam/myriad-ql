package org.lemonade;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.lemonade.gui.QLGui;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.FormVisitor;
import org.lemonade.visitors.GuiVisitor;
import org.lemonade.visitors.TypeCheckVisitor;

public class ParseTest {

    private String simpleForm;
    private String dateForm;

    private FormVisitor visitor = new FormVisitor();

    @Before
    public void setUp() throws Exception {

        simpleForm = "form name {" +
                "tmp1: \"Hoe groot is jouw decimal?\" decimal \n" +
                "if(((-2) + 4.0) * 8.0 >= tmp1) \n{tmp: \"yu\" money}" +
                "\ntmp1: \"ben dubbel\" money}";

        dateForm = "form name {\n" +
                "tmp1: \"Wanneer?\" date \n" +
                "if (11/12/2001 > tmp1) {tmp: \"oke?\" boolean}}";

    }

    @Test
    public void astTest() throws IOException{
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(dateForm));
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
        root.accept(typeCheckVisitor);
        if (typeCheckVisitor.hasErrors()){
            for (String error : typeCheckVisitor.getErrors()){
                System.err.println(error);
            }
        }
    }
}

