package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLLexer;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.gui.errors.ErrorDialogGenerator;
import com.matthewchapman.ql.validation.Validator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Contains app parsing logic, including building the QL AST from Antlr and handling type checking
 * before UI generation.
 */
public class ASTBuilder {

    private final ParseTreeErrorListener errorListener;
    private final ErrorDialogGenerator dialogGenerator;

    public ASTBuilder() {
        errorListener = new ParseTreeErrorListener();
        this.dialogGenerator = new ErrorDialogGenerator();
    }

    public Form buildQLAST(String input) {
        QLParser parser = getQlParser(input);
        Form form = getForm(parser);

        if (errorListener.getLogger().getErrorNumber() > 0) {
            dialogGenerator.generateErrorListBox(errorListener.getLogger().toString(), "Parser Error", "QL encountered an parsing error");
            return null;
        } else {
            return form;
        }
    }

    public Form getForm(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();
        return (Form) visitor.visit(formDeclarationContext);
    }

    public Statement getStatement(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.StatementContext statementContext = parser.statement();
        return (Statement) visitor.visit(statementContext);
    }

    public Expression getExpression(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.ExpressionContext expressionContext = parser.expression();
        return (Expression) visitor.visit(expressionContext);
    }

    public QLParser getQlParser(String input) {

        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        return parser;
    }

    public boolean validateAST(Form form) {

        return new Validator().runChecks(form);
    }
}
