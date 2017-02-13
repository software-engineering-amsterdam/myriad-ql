package org.ql.parser;

import org.ql.ast.Identifier;
import org.ql.ast.form.Form;
import org.ql.grammar.QLParserParser.*;

public class ASTFactory {
    public Form createAST(FormContext formContext) {
        return new Form(new Identifier(formContext.ID().getText()));
    }
}
