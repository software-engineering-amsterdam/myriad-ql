package org.ql.typechecker;

import org.ql.ast.*;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.If;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.Type;
import org.ql.grammar.QLParserParser;

public class TypeCheckerVisitor implements Visitor {

    @Override
    public void visit(Form astNode) {

    }

    @Override
    public void visit(Identifier identifier) {

    }

    @Override
    public void visit(Question node) {

    }

    @Override
    public void visit(If node) {

    }

    @Override
    public void visit(Statement node) {

    }

    @Override
    public void visit(QuestionText node) {

    }

    @Override
    public void visit(Negation node) {

    }

    @Override
    public void visit(Product node) {

    }

    @Override
    public void visit(Increment node) {

    }

    @Override
    public void visit(Subtraction node) {

    }

    @Override
    public void visit(NotEqual node) {

    }

    @Override
    public void visit(LogicalAnd node) {

    }

    @Override
    public void visit(LowerThan node) {

    }

    @Override
    public void visit(GreaterThanOrEqual node) {

    }

    @Override
    public void visit(Division node) {

    }

    @Override
    public void visit(Parameter node) {

    }

    @Override
    public void visit(Group node) {

    }

    @Override
    public void visit(Addition node) {

    }

    @Override
    public void visit(GreaterThan node) {

    }

    @Override
    public void visit(Decrement node) {

    }

    @Override
    public void visit(Equals node) {

    }

    @Override
    public void visit(LowerThanOrEqual node) {

    }

    @Override
    public void visit(LogicalOr node) {

    }

    @Override
    public void visit(BooleanLiteral node) {

    }

    @Override
    public void visit(DecimalLiteral node) {

    }

    @Override
    public void visit(IntegerLiteral node) {

    }

    @Override
    public void visit(StringLiteral node) {

    }

    @Override
    public void visit(Type node) {

    }
}