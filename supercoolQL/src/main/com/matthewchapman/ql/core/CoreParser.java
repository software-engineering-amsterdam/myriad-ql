package com.matthewchapman.ql.core;

import com.matthewchapman.antlr.QLLexer;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.parsing.MCQLErrorListener;
import com.matthewchapman.ql.parsing.MCQLVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Created by matt on 24/02/2017.
 */
public class CoreParser {

    //TODO this should not all be public
    public Form buildQLAST(String input) {
        MCQLErrorListener errorListener = new MCQLErrorListener();
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();
        MCQLVisitor visitor = new MCQLVisitor();

        return (Form) visitor.visit(formDeclarationContext);
    }
}
