package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.GUI.WidgetBuilder;
import javafx.scene.Node;

/**
 * Created by Tjarco, 21-03-17.
 */
public class IntegerType extends Type {
    @Override
    public Value defaultValue() {
        return new NumberValue("0");
    }

    @Override
    public Node typeNode(WidgetBuilder builder, Identifier identifier) {
        return builder.getNode(this, identifier);
    }

}
