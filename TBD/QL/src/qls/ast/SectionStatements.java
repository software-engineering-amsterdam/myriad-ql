package qls.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class SectionStatements extends SectionStatement {
    private final SectionStatement current;
    private final SectionStatements next;

    public SectionStatements(SectionStatement current, SectionStatements next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public SectionStatements(SectionStatement current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<SectionStatement> getStatements(){
        List<SectionStatement> statements = new ArrayList<>();
        if(current == null){
            return statements;
        }

        SectionStatements currentEntry = this;
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

    private SectionStatements next(){
        return next;
    }

    private SectionStatement getCurrentStatement(){
        return current;
    }
}
