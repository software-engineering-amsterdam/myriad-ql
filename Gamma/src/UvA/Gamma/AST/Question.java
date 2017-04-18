package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question extends IdentifiableFormItem {

    private Type type;

    public Question(String question, Identifier id, Type type) {
        this.label = question;
        this.identifier = id;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {
        return "<Question>: " + label + " " + identifier + ": " + type;
    }
}
