package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.GUI.WidgetBuilder;
import UvA.Gamma.Visitors.Visitor;
import javafx.scene.Node;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class Type implements ASTNode {
    private WidgetBuilder builder;

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean equalsType(Type type) {
        return type.getClass().getName().equals(this.getClass().getName());
    }

    public abstract Value defaultValue();

    public abstract Node typeNode(WidgetBuilder builder, Identifier identifier);
}