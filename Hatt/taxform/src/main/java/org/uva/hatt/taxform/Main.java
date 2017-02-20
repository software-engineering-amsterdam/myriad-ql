package org.uva.hatt.taxform;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.grammars.QLLexer;
import org.uva.hatt.taxform.grammars.QLParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = new ByteArrayInputStream("form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: money }".getBytes("utf-8"));
        InputStream inputStream = new ByteArrayInputStream("form taxOfficeExample { \n\"Did you sell a house in 2010?\" hasSoldHouse: boolean \n\"Did you buy a house in 2010?\" hasBoughtHouse: boolean }".getBytes("utf-8"));
//        InputStream inputStream = new ByteArrayInputStream("form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: string if (hasSoldHouse) { \"What was the selling price?\" sellingPrice: money \"What was the selling price?\" sellingPrice: money } }".getBytes("utf-8"));
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree tree = parser.form();
        System.out.println(tree.toStringTree(parser));

        QLVisitor visitor = new QLVisitor();
        visitor.visit(tree);

        Form form = visitor.getForm();
        System.out.println(form.toString());
    }

}
