package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;

import java.util.HashSet;
import java.util.Set;

public class DependencyVisitor implements ExpressionVisitor<Set<String>>{

    private Set<String> dependencies;

    public DependencyVisitor(){
        dependencies = new HashSet<>();
    }

    @Override
    public Set<String> visit(Addition addition){
        return visitExpression(addition);
    }

    @Override
    public Set<String> visit(Division division){
        return visitExpression(division);
    }

    @Override
    public Set<String> visit(Equal equal){
        return visitExpression(equal);
    }

    @Override
    public Set<String> visit(GreaterThan greaterThan){
        return visitExpression(greaterThan);
    }

    @Override
    public Set<String> visit(GreaterThanOrEqual greaterThanOrEqual){
        return visitExpression(greaterThanOrEqual);
    }

    @Override
    public Set<String> visit(LessThan lessThan){
        return visitExpression(lessThan);
    }

    @Override
    public Set<String> visit(LessThanOrEqual lessThanOrEqual){
        return visitExpression(lessThanOrEqual);
    }

    @Override
    public Set<String> visit(LogicalAnd logicalAnd){
        return visitExpression(logicalAnd);
    }

    @Override
    public Set<String> visit(LogicalOr logicalOr){
        return visitExpression(logicalOr);
    }

    @Override
    public Set<String> visit(Multiplication multiplication){
        return visitExpression(multiplication);
    }

    @Override
    public Set<String> visit(NotEqual notEqual){
        return visitExpression(notEqual);
    }

    @Override
    public Set<String> visit(Subtraction subtraction){
        return visitExpression(subtraction);
    }

    @Override
    public Set<String> visit(BooleanLiteral booleanLiteral){
        return dependencies;
    }

    @Override
    public Set<String> visit(IntegerLiteral integerLiteral){
        return dependencies;
    }

    @Override
    public Set<String> visit(StringerLiteral stringerLiteral){
        return dependencies;
    }

    @Override
    public Set<String> visit(GroupedExpression groupedExpression){
        return dependencies;
    }

    @Override
    public Set<String> visit(Identifier identifier){
        dependencies.add(identifier.getValue());
        return dependencies;
    }

    private Set<String> visitExpression(BooleanExpression expression)
    {
        expression.getLhs().accept(this);
        expression.getRhs().accept(this);
        return dependencies;
    }
}
