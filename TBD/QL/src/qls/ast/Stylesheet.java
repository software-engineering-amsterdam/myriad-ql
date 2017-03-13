package qls.ast;

import qls.ast.literals.QLSIdent;

/**
 * Created by rico on 7-3-17.
 */
public class Stylesheet extends ASTNode {
    private final String name;
    private final StylesheetPages statements;

    public Stylesheet(QLSIdent name, StylesheetPages statements) {
        super(name.getRowNumber());
        this.name = name.getValue();
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public StylesheetPages getStylesheetStatements() {
        return statements;
    }
}
