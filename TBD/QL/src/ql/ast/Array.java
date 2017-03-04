package ql.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 4-3-2017.
 */
public abstract class Array<T> extends ASTNode {

    private final T current;
    private final Array<T> next;

    public Array(T current, Array<T> next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public Array(T current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<T> getItems(){
        List<T> items = new ArrayList<>();

        if(current == null){
            return items;
        }

        Array<T> currentEntry = this;
        items.add(currentEntry.getCurrentStatement());

        while (currentEntry.hasNext()){
            currentEntry = currentEntry.next();
            items.add(currentEntry.getCurrentStatement());
        }

        return items;
    }

    private boolean hasNext(){
        return next != null;
    }

    private Array<T> next(){
        return next;
    }

    private T getCurrentStatement(){
        return current;
    }
}
