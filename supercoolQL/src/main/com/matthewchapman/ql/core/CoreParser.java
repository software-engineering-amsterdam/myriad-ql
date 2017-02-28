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

    public Form buildQLAST(String input) {

        //TODO this should not all be public

        //new error handler
        MCQLErrorListener errorListener = new MCQLErrorListener();

        // Get our lexer
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        // Attach custom error listener implementation
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        QLParser parser = new QLParser(tokens);
        // Attach custom error listener implementation
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        // Specify our entry point
        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();

        MCQLVisitor visitor = new MCQLVisitor();

        return (Form) visitor.visit(formDeclarationContext);

    }

}
