package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.collection.QuestionCollector;
import org.ql.collection.Questions;
import org.ql.symbol_table.SymbolTable;
import org.ql.typechecker.expression.NumberExpectedException;
import org.ql.typechecker.expression.TypeMismatchException;
import org.ql.typechecker.expression.UndefinedIdentifierException;

import java.util.List;

public class TypeChecker implements FormVisitor<Void, Void>, StatementVisitor<Void, Void>,
        ExpressionVisitor<Type, Void> {

    private final SymbolTable symbolTable = new SymbolTable();
    private final Messages messages = new Messages();

    @Override
    public Void visit(Form form, Void ignore) {
        Questions questions = QuestionCollector.collect(form);

        fillSymbolTable(questions);
        checkQuestionDuplicates(questions, null);
        checkIdentifier(form.getName(), null);
        checkStatements(form.getStatements(), null);

        return null;
    }

    @Override
    public Void visit(IfThen ifThen, Void ignore) {
        checkCondition(ifThen.getCondition(), null);
        checkStatements(ifThen.getThenStatements(), null);

        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse, Void ignore) {
        checkCondition(ifThenElse.getCondition(), null);
        checkStatements(ifThenElse.getThenStatements(), null);
        checkStatements(ifThenElse.getElseStatements(), null);

        return null;
    }

    @Override
    public Void visit(Question question, Void ignore) {

        checkIdentifier(question.getId(), null);

        checkQuestionText(question.getQuestionText(), null);

        Type valueType = question.getDefaultValue().accept(this, null);

        if (!question.getType().toString().equals(valueType.toString())) {
            messages.addError(new TypeMismatchException(question.getType(), valueType));
        }

        return null;
    }

    @Override
    public Type visit(Negation node, Void ignore) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!innerExpressionType.isBoolean()) {
            messages.addError(new TypeMismatchException(new BooleanType(), innerExpressionType));
            return new UnknownType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(Product node, Void ignore) {
        return checkTypeMismatch(node, null);
    }

    @Override
    public Type visit(Increment node, Void ignore) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpectedException(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Subtraction node, Void ignore) {
        return checkTypeMismatch(node, null);
    }

    @Override
    public BooleanType visit(NotEqual node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LogicalAnd node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LowerThan node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(GreaterThanOrEqual node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public Type visit(Division node, Void ignore) {
        return checkTypeMismatch(node, null);
    }

    @Override
    public Type visit(Parameter node, Void ignore) {
        if (!symbolTable.isDeclared(node.getId())) {
            messages.addError(new UndefinedIdentifierException(node.getId()));
            return new UnknownType();
        }

        return symbolTable.lookup(node.getId());
    }

    @Override
    public Type visit(Group node, Void ignore) {
        return node.getExpression().accept(this, null);
    }

    @Override
    public Type visit(Addition node, Void ignore) {
        return checkTypeMismatch(node, null);
    }

    @Override
    public BooleanType visit(GreaterThan node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public Type visit(Decrement node, Void ignore) {
        Type innerExpressionType = node.getExpression().accept(this, null);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpectedException(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public BooleanType visit(Equals node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LowerThanOrEqual node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LogicalOr node, Void ignore) {
        checkTypeMismatch(node, null);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(BooleanLiteral node, Void ignore) {
        return new BooleanType();
    }

    @Override
    public FloatType visit(DecimalLiteral node, Void ignore) {
        return new FloatType();
    }

    @Override
    public IntegerType visit(IntegerLiteral node, Void ignore) {
        return new IntegerType();
    }

    @Override
    public StringType visit(StringLiteral node, Void ignore) {
        return new StringType();
    }

    private Type checkTypeMismatch(BinaryExpression node, Void ignore) {
        Type leftType = node.getLeft().accept(this, null);
        Type rightType = node.getRight().accept(this, null);

        if (!leftType.equals(rightType)) {
            messages.addError(new TypeMismatchException(leftType, rightType));

            return new UnknownType();
        }

        return leftType;
    }

    private void checkIdentifier(Identifier identifier, Void ignore) {
        if (identifier.toString().isEmpty()) {
            messages.addError("Identifier cannot be empty", identifier);
        }
    }

    private void checkQuestionDuplicates(Questions questions, Void ignore) {
        for (Question question : questions) {
            if (questions.hasDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' has duplicate(s)", question);
            }
            if (questions.hasLabelDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' label has duplicate(s)", question);
            }
        }
    }

    private void checkStatements(List<Statement> statements, Void ignore) {
        for (Statement statement : statements) {
            statement.accept(this, null);
        }
    }

    private void checkCondition(Expression condition, Void ignore) {
        Type conditionType = condition.accept(this, null);

        if (!conditionType.isBoolean()) {
            messages.addError(new TypeMismatchException(new BooleanType(), conditionType));
        }
    }

    private void checkQuestionText(QuestionText questionText, Void ignore) {
        if (questionText.toString().isEmpty()) {
            messages.addError("No question text found", questionText);
        }
    }

    private void fillSymbolTable(List<Question> questions) {
        for (Question question : questions) {
            symbolTable.declare(question.getId(), question.getType());
        }
    }
}
