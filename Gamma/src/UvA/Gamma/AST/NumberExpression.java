package UvA.Gamma.AST;
import UvA.Gamma.AST.Values.Number;

/**
 * Created by Tjarco, 14-02-17.
 */
public class NumberExpression implements Expression {


    public Number evaluate(){
        return new Number(0);
    }

    @Override
    public String toString() {
        Number n  = evaluate();
        if (n.isInteger()){
            return "" + n.intValue();
        }else{
            return "" + n.doubleValue();
        }
    }
}
