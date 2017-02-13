package org.ql.ast.form;

import org.ql.ast.Identifier;
import org.ql.ast.declaration.Declaration;

import java.util.ArrayList;

public class Form {
    private final Identifier name;
    private final ArrayList<Declaration> declarations;

    public Form(Identifier name, ArrayList<Declaration> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public Identifier getName() {
        return name;
    }

    public ArrayList<Declaration> getDeclarations() {
        return declarations;
    }

    public Declaration getDeclaration(int index) {
        return declarations.get(index);
    }
}
