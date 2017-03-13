package qls.ast;

import qls.ast.attributes.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class Attributes extends ASTNode {
    private final Attribute current;
    private final Attributes next;

    public Attributes(Attribute current, Attributes next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public Attributes(Attribute current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<Attribute> getAttributes(){
        List<Attribute> attributes = new ArrayList<>();
        if(current == null){
            return attributes;
        }

        Attributes currentEntry = this;
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

    private Attributes next(){
        return next;
    }

    private Attribute getCurrentAttribute(){
        return current;
    }

}
