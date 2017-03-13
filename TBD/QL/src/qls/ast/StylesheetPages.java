package qls.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class StylesheetPages extends ASTNode {
    private final Page current;
    private final StylesheetPages next;

    public StylesheetPages(Page current, StylesheetPages next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public StylesheetPages(Page current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<Page> getStatements(){
        List<Page> statements = new ArrayList<>();
        if(current == null){
            return statements;
        }

        StylesheetPages currentEntry = this;
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

    private StylesheetPages next(){
        return next;
    }

    private Page getCurrentStatement(){
        return current;
    }
}
