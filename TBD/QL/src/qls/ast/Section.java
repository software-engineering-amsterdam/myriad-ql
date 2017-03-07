package qls.ast;

import qls.ast.literals.QLSIdent;

/**
 * Created by rico on 7-3-17.
 */
public class Section extends ASTNode {
    private final String name;
    private final SectionStatements statements;

    public Section(QLSIdent name, SectionStatements statements) {
        super(name.getRowNumber());
        this.name = name.getValue();
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public SectionStatements getSectionStatements() {
        return statements;
    }
}
