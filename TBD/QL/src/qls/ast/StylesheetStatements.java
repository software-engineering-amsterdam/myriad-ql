package qls.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class StylesheetStatements extends ASTNode {
    private final StylesheetStatement current;
    private final StylesheetStatements next;

    public StylesheetStatements(StylesheetStatement current, StylesheetStatements next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public StylesheetStatements(StylesheetStatement current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<StylesheetStatement> getStatements(){
        List<StylesheetStatement> statements = new ArrayList<>();
        if(current == null){
            return statements;
        }

        StylesheetStatements currentEntry = this;
        statements.add(currentEntry.getCurrentStatement());

        while (currentEntry.hasNext()){
            currentEntry = currentEntry.next();
            statements.add(currentEntry.getCurrentStatement());
        }

        return statements;
    }

    private boolean hasNext(){
        return next != null;
    }

    private StylesheetStatements next(){
        return next;
    }

    private StylesheetStatement getCurrentStatement(){
        return current;
    }
}
