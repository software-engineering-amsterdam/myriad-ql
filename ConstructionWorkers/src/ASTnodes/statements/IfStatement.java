/**
 * IfStatement.java.
 */

package ASTnodes.statements;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;
import ASTnodes.expressions.Expression;

import java.util.List;

public class IfStatement extends Statement {

    private final Expression expression;
    private final List<Statement> statements;

    public IfStatement(Expression expression, List<Statement> statements, CodeLocation location) {
        super(location);
        this.expression = expression;
        this.statements = statements;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Statement> getStatements() {
        return statements;
    }

//    @Override
//    public String toString() {
//        String myString = "\n if (" + this.expression.toString() + ") {\n";
//
//        for (Statement statement : this.statements) {
//            myString += (statement.toString() + "\n");
//        }
//        myString += "\n}";
//        return myString;
//    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
