/**
 * IfStatement.java.
 */

package ASTnodes.statements;

import ASTnodes.CodeLocation;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;
import ASTnodes.visitors.FormAndStatementVisitor;
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

    public Type getType(Type type) {

        String booleanTest = new BooleanType().getClass().getName();

        String typeString = type.getClass().getName();

        if (typeString == booleanTest)
            return type;
        else
            return new UndefinedType();
    }

    @Override
    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
