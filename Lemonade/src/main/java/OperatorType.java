/**
 * Created by dimitribelfor on 07/02/2017.
 * Wrapper for the operator enumerators.
 */
public class OperatorType {
    public enum BinaryOperator {
        AND, OR, GT, LT, LE, GE, NOT_EQUAL, EQUAL_EQUAL, PLUS,
        MINUS, PRODUCT, DIVIDE;

        public String toString() {
            switch (this) {
                case AND:
                    return "&&";
                case OR:
                    return "||";
                case GT:
                    return ">";
                case LT:
                    return "<";
                case LE:
                    return "<=";
                case GE:
                    return ">=";
                case NOT_EQUAL:
                    return "!=";
                case EQUAL_EQUAL:
                    return "==";
                case PLUS:
                    return "+";
                case MINUS:
                    return "-";
                case PRODUCT:
                    return "*";
                case DIVIDE:
                    return "/";
                default:
                    return "";
            }
        }
    }

    public enum UnaryOperator{
        BANG, MINUS, LIT;
        public String toString() {
            switch(this) {
                case BANG:
                    return "!";
                case MINUS:
                    return "-";
                case LIT:
                    return "";
                default: return "";
            }
        }
    }
}
