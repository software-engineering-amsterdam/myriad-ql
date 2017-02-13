package org.uva.hatt.taxform.grammars;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.uva.hatt.taxform.grammars.QLLexer;
import org.uva.hatt.taxform.grammars.QLParser;

import static org.junit.Assert.assertEquals;

public class QLTest {


    @Test
    public void test() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("form taxOfficeExample { }".getBytes());
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.form();

        assertEquals(tree.toStringTree(parser), "(form form (formId taxOfficeExample) { })");
    }

}
