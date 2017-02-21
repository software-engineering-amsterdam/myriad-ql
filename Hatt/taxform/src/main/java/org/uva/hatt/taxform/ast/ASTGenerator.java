package org.uva.hatt.taxform.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.grammars.QLLexer;
import org.uva.hatt.taxform.grammars.QLParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASTGenerator {

    public static Form getForm(String form) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(form.getBytes("utf-8"));
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree tree = parser.form();
        System.out.println(tree.toStringTree(parser));

        QLVisitor visitor = new QLVisitor();
        visitor.visit(tree);

        return visitor.getForm();
    }

}
