package org.lemonade;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.lemonade.nodes.Form;
import org.lemonade.visitors.TypeCheckVisitor;


import java.io.StringReader;

/**
 *
 */
public class Walker {
    public static void main(String[] args) throws Exception {
        String simpleForm = "form naam {tmp : \"echt?\" boolean" +
                " if(true) { tmp2: \"ja?\"boolean}" +
                "}";

        String formExpression = "form name {if(1+1) {tmp: \"yu\" boolean}}";
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(formExpression));

        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.form();
        QLFormVisitor visitor = new QLFormVisitor();
        Form root = (Form) tree.accept(visitor);

        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        root.accept(typeCheckVisitor);

    }
}
