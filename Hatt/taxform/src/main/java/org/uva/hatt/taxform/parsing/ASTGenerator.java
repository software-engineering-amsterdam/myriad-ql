package org.uva.hatt.taxform.parsing;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.grammars.QLLexer;
import org.uva.hatt.taxform.grammars.QLParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASTGenerator {

    public static Form getForm(String form) throws IOException {
        QLParser parser = getQlParser(form);
        ParseTree tree = parser.form();

        ASTBuilder visitor = new ASTBuilder();
        visitor.visit(tree);

        return visitor.getForm();
    }

    public static ParseTree getParseTree(String form) throws IOException {
        QLParser parser = getQlParser(form);
        return parser.form();
    }

    public static String getParseStringTree(String form) throws IOException {
        QLParser parser = getQlParser(form);
        ParseTree tree = parser.form();

        return tree.toStringTree(parser);
    }

    private static QLParser getQlParser(String form) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(form.getBytes("utf-8"));
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new QLParser(tokens);
    }

}
