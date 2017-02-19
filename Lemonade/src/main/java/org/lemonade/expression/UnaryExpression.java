package org.lemonade.expression;

import org.lemonade.QLOperatorType;
import org.lemonade.QLType;

//public class UnaryExpression extends Expression {
//    private QLOperatorType.UnaryOperator operator;
//
//    public UnaryExpression(QLType type, QLOperatorType.UnaryOperator operator, int value) {
//        super(type, value);
//        this.operator = operator;
//    }
//}
public abstract class UnaryExpression extends Expression {
    private QLOperatorType operator;
    private Expression expression;

    public Expression getExpression() {
        return this.expression;
    }
    
    public QLOperatorType getOperator() {
        return null;
    }

}