package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.BooleanType;
import UvA.Gamma.AST.Types.DateType;
import UvA.Gamma.AST.Types.MoneyType;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Antlr.QL.QLParser;
import javafx.scene.layout.GridPane;

/**
 * Created by casboot on 10-04-17.
// */

public interface  WidgetBuilder {

    void getWidget(Computed com);
    void getWidget (Question ques);
    void getWidget (Condition con);
//
//   public static widgetObject getWidget(Condition con){
//        return con.getType().widget;
////    }
//


}
