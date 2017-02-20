package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
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

public class TypeCheckerVisitor implements AbstractTypeCheckerVisitor {

    @Override
    public void visitForm(Form ctx) {

    }

    @Override
    public void visitQuestion(Question ctx) {

    }

    @Override
    public void visitIf(If ctx) {

    }

    @Override
    public void visitStatement(Statement ctx) {

    }

    @Override
    public void visitQuestionText(QuestionText nctx) {

    }

    @Override
    public void visitDecimalLiteral(DecimalLiteral ctx) {

    }

    @Override
    public void visitNegation(Negation ctx) {

    }

    @Override
    public void visitProduct(Product ctx) {

    }

    @Override
    public void visitIncrement(Increment ctx) {

    }

    @Override
    public void visitSubtraction(QLParserParser.SubtractionContext ctx) {

    }

    @Override
    public void visitNotEqual(NotEqual ctx) {

    }

    @Override
    public void visitLogicalAnd(LogicalAnd ctx) {

    }

    @Override
    public void visitLowerThan(LowerThan ctx) {

    }

    @Override
    public void visitGreaterThanOrEqual(GreaterThanOrEqual ctx) {

    }

    @Override
    public void visitDivision(Division ctx) {

    }

    @Override
    public void visitParameter(Parameter ctx) {

    }

    @Override
    public void visitIdentifier(Identifier ctx) {

    }

    @Override
    public void visitBooleanLiteral(BooleanLiteral ctx) {

    }

    @Override
    public void visitGroup(Group ctx) {

    }

    @Override
    public void visitAddition(QLParserParser.AdditionContext ctx) {

    }

    @Override
    public void visitGreaterThan(GreaterThan ctx) {

    }

    @Override
    public void visitStringLiteral(StringLiteral ctx) {

    }

    @Override
    public void visitDecrement(Decrement ctx) {

    }

    @Override
    public void visitEquals(Equals ctx) {

    }

    @Override
    public void visitLowerThanOrEqual(LowerThanOrEqual ctx) {

    }

    @Override
    public void visitIntegerLiteral(IntegerLiteral ctx) {

    }

    @Override
    public void visitLogicalOr(LogicalOr ctx) {

    }

    @Override
    public void visitTypeBooleanLiteral(BooleanLiteral ctx) {

    }

    @Override
    public void visitTypeDecimalLiteral(DecimalLiteral ctx) {

    }

    @Override
    public void visitTypeIntegerLiteral(IntegerLiteral ctx) {

    }

    @Override
    public void visitTypeString(StringLiteral ctx) {

    }

    @Override
    public void visitType(Type ctx) {

    }
}