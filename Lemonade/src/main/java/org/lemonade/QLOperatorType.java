package org.lemonade;

public class QLOperatorType {
    public enum UnaryOperator {
        BANG, MINUS, LIT;

        public String toString() {
            switch (this) {
                case BANG:
                    return "!";
                case MINUS:
                    return "-";
                case LIT:
                    return "";
                default:
                    return "";
            }
        }
    }

    public enum BinaryOperator {
        AND, OR, GT, LT, LE, GE, NOT_EQUAL, EQUAL_EQUAL, PLUS,
        MINUS, PRODUCT, DIVIDE;

        public String toString() {
            switch (this) {
                case PRODUCT:
                    return "*";
                case DIVIDE:
                    return "/";
                case PLUS:
                    return "+";
                case MINUS:
                    return "-";
                case LT:
                    return "<";
                case LE:
                    return "<=";
                case GT:
                    return ">";
                case GE:
                    return ">=";
                case EQUAL_EQUAL:
                    return "==";
                case NOT_EQUAL:
                    return "!=";
                case AND:
                    return "&&";
                case OR:
                    return "||";
                default:
                    return "";
            }
        }
    }
}
