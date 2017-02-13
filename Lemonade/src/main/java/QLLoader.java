import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class QLLoader extends QLBaseListener {
    private Stack<Object> stack = new Stack<>();

    @Override
    public void exitForm(QLParser.FormContext ctx) {
        String id = ctx.identifier().getText();
    }

    public void exitExpression() {

    }

}

