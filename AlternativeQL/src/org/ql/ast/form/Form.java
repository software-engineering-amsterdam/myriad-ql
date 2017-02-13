package org.ql.ast.form;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.ql.ast.declaration.Declaration;

import java.util.List;

public class Form implements Node {
    private final Identifier name;
    private final List<Declaration> declarations;

    public Form(Identifier name, List<Declaration> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public Identifier getName() {
        return name;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public Declaration getDeclaration(int index) {
        return declarations.get(index);
    }
}
