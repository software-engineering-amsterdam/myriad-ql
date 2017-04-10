package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by casboot on 09-04-17.
 */
public class UIVisitor extends BaseVisitor {

    @Override
    public void visit(Computed computed) {

    }

    @Override
    public void visit(Question question) {

    }

    @Override
    public void visit(Condition condition) {

    }


}

//    In de visitor zelf vraag je elke keer dat je een widget nodig hebt dit aan het interface WidgetBuilder
//    bijvoorbeeld:
//    visit(Computed c){ WidgetBuilder.getWidget(Computed c); }
//    oid
//    of showQuestion() kan inderdaad ook, maar dat impliceert dat die builder ook daadwerkelijk de widget gaat tonen, misschien is getWidget wel beter