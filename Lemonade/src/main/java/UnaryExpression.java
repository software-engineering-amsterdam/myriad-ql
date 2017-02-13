/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class UnaryExpression extends Expression {
    private OperatorType.UnaryOperator operator;

    public UnaryExpression(Types type, OperatorType.UnaryOperator operator, int  value) {
        super(type, value);
        this.operator = operator;
    }
}
