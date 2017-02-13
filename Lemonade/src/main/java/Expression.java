/**
 * Created by dimitribelfor on 07/02/2017.
 */
public abstract class Expression {
    private Types type;

    private OperatorType operator;

    private int left;
    public Expression (Types type, int value) {
        this.type = type;
        this.left = value;
    }

    public int getLeft() {
        return left;
    }

    public OperatorType getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return Integer.toString(this.left);
    }
}
