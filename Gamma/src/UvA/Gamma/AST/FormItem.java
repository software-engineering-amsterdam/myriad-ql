package UvA.Gamma.AST;

import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface FormItem extends ASTNode {
    void idChanged(Form root, FormItem changed, String value);

    void accept(Visitor visitor);
}
