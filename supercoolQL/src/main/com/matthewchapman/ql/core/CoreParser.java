package com.matthewchapman.ql.core;

import com.matthewchapman.antlr.QLLexer;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.parsing.AntlrErrorListener;
import com.matthewchapman.ql.parsing.AntlrVisitor;
import com.matthewchapman.ql.validation.validator.QLTreeVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Created by matt on 24/02/2017.
 */
class CoreParser {

    //TODO this should not all be public
    Form buildQLAST(String input) {
        AntlrErrorListener errorListener = new AntlrErrorListener();
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();
        AntlrVisitor visitor = new AntlrVisitor();

        return (Form) visitor.visit(formDeclarationContext);

    }

    void visitAST(Form form)
    {
        QLTreeVisitor visitor = new QLTreeVisitor();

        for (Statement statement:form.getStatements()) {
            statement.accept(visitor);
        }
    }
}
