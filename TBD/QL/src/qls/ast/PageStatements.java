package qls.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class PageStatements extends ASTNode {
    private final PageStatement current;
    private final PageStatements next;

    public PageStatements(PageStatement current, PageStatements next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public PageStatements(PageStatement current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<PageStatement> getStatements(){
        List<PageStatement> statements = new ArrayList<>();
        if(current == null){
            return statements;
        }

        PageStatements currentEntry = this;
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

    private PageStatements next(){
        return next;
    }

    private PageStatement getCurrentStatement(){
        return current;
    }
}
