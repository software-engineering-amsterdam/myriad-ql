package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.*;
import javafx.scene.Node;

import java.util.List;

/**
 * Created by casboot on 10-04-17.
 * //
 */

public interface WidgetBuilder {

    List<Node> getWidget(Computed com);

    List<Node> getWidget(Question ques);

    Node getNode(BooleanType type, Identifier identifier);

    Node getNode(DateType type, Identifier identifier);

    Node getNode(IntegerType type, Identifier identifier);

    Node getNode(DecimalType type, Identifier identifier);

    Node getNode(MoneyType type, Identifier identifier);

//
//   public static widgetObject getWidget(Condition con){
//        return con.getType().widget;
////    }
//


}
