package qls.ast;

import qls.ast.attributes.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class DefaultStatements extends ASTNode {
    private final ObjectStatement current;
    private final DefaultStatements next;

    public DefaultStatements(ObjectStatement current, DefaultStatements next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public DefaultStatements(ObjectStatement current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<ObjectStatement> getAttributes(){
        List<ObjectStatement> attributes = new ArrayList<>();
        if(current == null){
            return attributes;
        }

        DefaultStatements currentEntry = this;
        attributes.add(currentEntry.getCurrentAttribute());

        while (currentEntry.hasNext()){
            currentEntry = currentEntry.next();
            attributes.add(currentEntry.getCurrentAttribute());
        }

        return attributes;
    }

    private boolean hasNext(){
        return next != null;
    }

    private DefaultStatements next(){
        return next;
    }

    private ObjectStatement getCurrentAttribute(){
        return current;
    }

}
