/**
 * Created by LGGX on 07-Mar-17.
 *
 * TODO: Possibly rename this class.
 */

package ql.semanticchecker;

import ql.astnodes.Form;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.Addition;
import ql.astnodes.expressions.binaries.numerical.Division;
import ql.astnodes.expressions.binaries.numerical.Multiplication;
import ql.astnodes.expressions.binaries.numerical.Subtraction;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.Negation;
import ql.astnodes.expressions.unaries.Negative;
import ql.astnodes.expressions.unaries.Parentheses;
import ql.astnodes.expressions.unaries.Positive;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.visitors.FormAndStatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfStatementVisitor implements FormAndStatementVisitor<Void>, ExpressionVisitor<Void> {

    private final List<String> identifierList = new ArrayList<>();
    private Boolean undefinedIdentifier;

    public IfStatementVisitor(IfStatement ifStatement, Expression expression) {
        this.undefinedIdentifier = false;
        expression.accept(this);
        ifStatement.accept(this);
    }

    public Boolean getUndefinedIdentifier() {
        return this.undefinedIdentifier;
    }

    @Override
    public Void visit(Form form) {
        return null;
    }

    @Override
    public Void visit(SimpleQuestion statement) {
        if(identifierList.contains(statement.getIdentifier().getName())) {
            this.undefinedIdentifier = true;
        }
        return null;
    }

    @Override
    public Void visit(ComputedQuestion statement) {
        if(identifierList.contains(statement.getIdentifier().getName())) {
            this.undefinedIdentifier = true;
        }
        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(MyBoolean literal) {
        return null;
    }

    @Override
    public Void visit(MyInteger literal) {
        return null;
    }

    @Override
    public Void visit(MyString literal) {
        return null;
    }

    @Override
    public Void visit(Identifier literal) {
        identifierList.add(literal.getName());
        return null;
    }

    @Override
    public Void visit(Money literal) {
        return null;
    }

    @Override
    public Void visit(Parentheses expression) {
        return null;
    }

    @Override
    public Void visit(Negation expression) {
        return null;
    }

    @Override
    public Void visit(Negative expression) {
        return null;
    }

    @Override
    public Void visit(Positive expression) {
        return null;
    }

    @Override
    public Void visit(AND expression) {
        return null;
    }

    @Override
    public Void visit(OR expression) {
        return null;
    }

    @Override
    public Void visit(EQ expression) {
        return null;
    }

    @Override
    public Void visit(NEQ expression) {
        return null;
    }

    @Override
    public Void visit(GT expression) {
        return null;
    }

    @Override
    public Void visit(LT expression) {
        return null;
    }

    @Override
    public Void visit(GTEQ expression) {
        return null;
    }

    @Override
    public Void visit(LTEQ expression) {
        return null;
    }

    @Override
    public Void visit(Addition expression) {
        return null;
    }

    @Override
    public Void visit(Subtraction expression) {
        return null;
    }

    @Override
    public Void visit(Multiplication expression) {
        return null;
    }

    @Override
    public Void visit(Division expression) {
        return null;
    }
}
