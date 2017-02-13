/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class BinaryExpression extends Expression {
    private int right;
    private OperatorType.BinaryOperator operator;
    BinaryExpression (Types type, OperatorType.BinaryOperator operator, int left, int right) {
        super(type, left);
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return Integer.toString(super.getLeft()) + " " + operator.toString() + " " + Integer.toString(this.right);
    }
}
