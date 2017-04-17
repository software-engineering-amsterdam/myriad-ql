package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.Visitors.Visitor;
import UvA.Gamma.GUI.DefaultWidgetBuilder;
import UvA.Gamma.GUI.WidgetBuilder;

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

    public DateType returnType(){

    }
}
//    misschien dan aan type een methode toevoegen en dan iets als: widget = type.getWidget(this); en dat elke type dan op
//        die Widgetbuilder de juiste methode aanroept