package UvA.Gamma.AST;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Form implements ASTNode {
    private List<FormItem> formItems;

    public Form() {
        formItems = new ArrayList<>();
    }

    public void addFormItem(FormItem item) {
        formItems.add(item);
    }

    public List<FormItem> getFormItems() {
        return formItems;
    }

    public void idChanged(String id, String newValue) {
        formItems.forEach(item -> item.idChanged(this, id, newValue));
    }
}
