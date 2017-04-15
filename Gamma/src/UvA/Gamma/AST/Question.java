package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Validation.TypeChecker;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question extends IdentifiableFormItem {

    public Question(String question, Identifier id, Type type) {
        this.label = question;
        this.identifier = id;
        this.type = type;
    }

    public boolean check(TypeChecker checker, String newValue) {
        assert type != null;
        return false;
    }


    @Override
    public void accept(Visitor visitor) {
        System.out.println(this.type.toString());
        visitor.visit(this);
    }


    @Override
    public String toString() {
        return "<Question>: " + label + " " + identifier + ": " + type;
    }

//    public Type returnType(){
//        return (this.type);
//    }
}
