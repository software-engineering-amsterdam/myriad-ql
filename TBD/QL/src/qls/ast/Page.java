package qls.ast;

import qls.ast.literals.QLSIdent;

/**
 * Created by rico on 7-3-17.
 */
public class Page extends ASTNode {
    private final String name;
    private final PageStatements pageStatements;

    public Page(QLSIdent name, PageStatements pageStatements) {
        super(name.getRowNumber());
        this.name = name.getValue();
        this.pageStatements = pageStatements;
    }

    public String getName() {
        return name;
    }

    public PageStatements getPageStatements() {
        return pageStatements;
    }
}
