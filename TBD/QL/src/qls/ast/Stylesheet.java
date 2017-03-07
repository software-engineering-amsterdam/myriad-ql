package qls.ast;

import qls.ast.*;
import qls.ast.literals.QLSIdent;

/**
 * Created by rico on 7-3-17.
 */
public class Stylesheet extends ASTNode {
    private final String name;
    private final StylesheetStatements statements;

    public Stylesheet(QLSIdent name, StylesheetStatements statements) {
        super(name.getRowNumber());
        this.name = name.getValue();
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public StylesheetStatements getStylesheetStatements() {
        return statements;
    }
}
