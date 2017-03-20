package UvA.Gamma.AST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Form implements ASTNode, Iterable<FormItem> {
    private List<FormItem> formItems;
    private String id;

    public Form(String id) {
        formItems = new ArrayList<>();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addFormItem(FormItem item) {
        formItems.add(item);
    }

    public void idChanged(FormItem changed, String newValue) {
        formItems.forEach(item -> item.idChanged(this, changed, newValue));
    }

    @Override
    public Iterator<FormItem> iterator() {
        return formItems.iterator();
    }

    public Stream<FormItem> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
