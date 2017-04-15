package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.GUI.WidgetBuilder;

/**
 * Created by casboot on 09-04-17.
 */
public class UIVisitor extends BaseVisitor {

    @Override
    public void visit(Computed com) {
        WidgetBuilder.getWidget(com);
    }

    @Override
    public void visit(Condition con) {
        WidgetBuilder.getWidget(con);
    }

    @Override
    public void visit(Question ques) {
        WidgetBuilder.getWidget(ques);
    }




}

//    In de visitor zelf vraag je elke keer dat je een widget nodig hebt dit aan het interface WidgetBuilder
//    bijvoorbeeld:
//    visit(Computed c){ WidgetBuilder.getWidget(Computed c); }
//    oid
//    of showQuestion() kan inderdaad ook, maar dat impliceert dat die builder ook daadwerkelijk de widget gaat tonen, misschien is getWidget wel beter