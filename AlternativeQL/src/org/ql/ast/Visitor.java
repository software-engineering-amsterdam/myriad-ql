package org.ql.ast;

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

public interface Visitor {
    void visit(Form node);
    void visit(Identifier identifier);
    void visit(Question node);
    void visit(If node);
    void visit(Statement node);
    void visit(QuestionText node);
    void visit(Negation node);
    void visit(Product node);
    void visit(Increment node);
    void visit(Subtraction node);
    void visit(NotEqual node);
    void visit(LogicalAnd node);
    void visit(LowerThan node);
    void visit(GreaterThanOrEqual node);
    void visit(Division node);
    void visit(Parameter node);
    void visit(Group node);
    void visit(Addition node);
    void visit(GreaterThan node);
    void visit(Decrement node);
    void visit(Equals node);
    void visit(LowerThanOrEqual node);
    void visit(LogicalOr node);
    void visit(BooleanLiteral node);
    void visit(DecimalLiteral node);
    void visit(IntegerLiteral node);
    void visit(StringLiteral node);
    void visit(Type node);
}
