package qls.ast;

import qls.ast.literals.QLSIdent;

/**
 * Created by rico on 7-3-17.
 */
public class Question extends Statement {
    private final String id;



    public Question(QLSIdent id) {
        super(id.getRowNumber());
        this.id = id.getValue();
    }

    public String getId() {
        return id;
    }

}
