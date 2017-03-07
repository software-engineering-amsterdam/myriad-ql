package qls.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 7-3-17.
 */
public class DefaultAttributes extends DefaultAttribute {
    private final DefaultAttribute current;
    private final DefaultAttributes next;

    public DefaultAttributes(DefaultAttribute current, DefaultAttributes next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public DefaultAttributes(DefaultAttribute current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<DefaultAttribute> getAttributes(){
        List<DefaultAttribute> attributes = new ArrayList<>();
        if(current == null){
            return attributes;
        }

        DefaultAttributes currentEntry = this;
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

    private DefaultAttributes next(){
        return next;
    }

    private DefaultAttribute getCurrentAttribute(){
        return current;
    }

}
