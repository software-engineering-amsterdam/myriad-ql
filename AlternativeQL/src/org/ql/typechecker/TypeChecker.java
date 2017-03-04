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
import org.ql.typechecker.circular_dependencies.CircularDependenciesResolver;
import org.ql.typechecker.circular_dependencies.DependencyPair;
import org.ql.typechecker.circular_dependencies.DependencySet;
import org.ql.typechecker.error.*;

import java.util.List;

public class TypeChecker implements FormVisitor<Void, Void>, StatementVisitor<Void, Void>,
        ExpressionVisitor<Type, Identifier> {

    private final Messages messages;
    private final SymbolTable symbolTable;
    private final CircularDependenciesResolver circularDependenciesResolver;

    public TypeChecker(Messages messages, SymbolTable symbolTable, CircularDependenciesResolver circularDependenciesResolver) {
        this.messages = messages;
        this.symbolTable = symbolTable;
        this.circularDependenciesResolver = circularDependenciesResolver;
    }

    @Override
    public Void visit(Form form, Void ignore) {
        Questions questions = QuestionCollector.collect(form);

        fillSymbolTable(questions);
        checkQuestionDuplicates(questions);
        checkIdentifier(form.getName());
        checkStatements(form.getStatements());

        checkCircularDependencies();

        return null;
    }

    @Override
    public Void visit(IfThen ifThen, Void ignore) {
        checkCondition(ifThen.getCondition());
        checkStatements(ifThen.getThenStatements());

        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse, Void ignore) {
        checkCondition(ifThenElse.getCondition());
        checkStatements(ifThenElse.getThenStatements());
        checkStatements(ifThenElse.getElseStatements());

        return null;
    }

    @Override
    public Void visit(Question question, Void ignore) {

        checkIdentifier(question.getId());

        checkQuestionText(question.getQuestionText());

        if (question.getValue() != null) {
            Type valueType = question.getValue().accept(this, question.getId());

            if (!question.getType().isCompatibleWith(valueType)) {
                messages.addError(new TypeMismatch(question.getType(), valueType));
            }
        }

        return null;
    }

    @Override
    public Type visit(Negation node, Identifier questionId) {
        Type innerExpressionType = node.getExpression().accept(this, questionId);

        if (!innerExpressionType.isBoolean()) {
            messages.addError(new TypeMismatch(new BooleanType(), innerExpressionType));
            return new UnknownType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(Product node, Identifier questionId) {
        return checkTypeMismatch(node, questionId);
    }

    @Override
    public Type visit(Increment node, Identifier questionId) {
        Type innerExpressionType = node.getExpression().accept(this, questionId);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpected(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Subtraction node, Identifier questionId) {
        return checkTypeMismatch(node, questionId);
    }

    @Override
    public BooleanType visit(NotEqual node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public Type visit(LogicalAnd node, Identifier questionId) {
        return checkLogicalExpression(node, questionId);
    }

    @Override
    public BooleanType visit(LowerThan node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(GreaterThanOrEqual node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public Type visit(Division node, Identifier questionId) {
        return checkTypeMismatch(node, questionId);
    }

    @Override
    public Type visit(Parameter parameter, Identifier questionId) {
        if (!symbolTable.isDeclared(parameter.getId())) {
            messages.addError(new UndefinedIdentifier(parameter.getId()));
            return new UnknownType();
        }

        circularDependenciesResolver.register(new DependencyPair(questionId, parameter.getId()));

        return symbolTable.lookup(parameter.getId());
    }

    @Override
    public Type visit(Group node, Identifier questionId) {
        return node.getExpression().accept(this, questionId);
    }

    @Override
    public Type visit(Addition node, Identifier questionId) {
        return checkTypeMismatch(node, questionId);
    }

    @Override
    public BooleanType visit(GreaterThan node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public Type visit(Decrement node, Identifier questionId) {
        Type innerExpressionType = node.getExpression().accept(this, questionId);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpected(innerExpressionType));
            return new UnknownType();
        }

        return innerExpressionType;
    }

    @Override
    public BooleanType visit(Equals node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LowerThanOrEqual node, Identifier questionId) {
        checkTypeMismatch(node, questionId);

        return new BooleanType();
    }

    @Override
    public Type visit(LogicalOr node, Identifier questionId) {
        return checkLogicalExpression(node, questionId);
    }

    @Override
    public BooleanType visit(BooleanLiteral node, Identifier questionId) {
        return new BooleanType();
    }

    @Override
    public FloatType visit(DecimalLiteral node, Identifier questionId) {
        return new FloatType();
    }

    @Override
    public IntegerType visit(IntegerLiteral node, Identifier questionId) {
        return new IntegerType();
    }

    @Override
    public StringType visit(StringLiteral node, Identifier questionId) {
        return new StringType();
    }

    private Type checkTypeMismatch(BinaryExpression node, Identifier questionId) {
        Type leftType = node.getLeft().accept(this, questionId);
        Type rightType = node.getRight().accept(this, questionId);

        if (!leftType.isCompatibleWith(rightType)) {
            messages.addError(new TypeMismatch(leftType, rightType));

            return new UnknownType();
        }

        return leftType;
    }

    private Type checkLogicalExpression(BinaryExpression node, Identifier questionId) {
        Type leftType = node.getLeft().accept(this, questionId);
        Type rightType = node.getRight().accept(this, questionId);

        if (!leftType.isBoolean()) {
            messages.addError(new TypeMismatch(new BooleanType(), leftType));

            return new UnknownType();
        }

        if (!rightType.isBoolean()) {
            messages.addError(new TypeMismatch(new BooleanType(), rightType));

            return new UnknownType();
        }

        return new BooleanType();
    }

    private void checkIdentifier(Identifier identifier) {
        if (identifier.toString().isEmpty()) {
            messages.addError(new EmptyIdentifier(identifier));
        }
    }

    private void checkQuestionDuplicates(Questions questions) {
        for (Question question : questions) {
            if (questions.hasDuplicates(question)) {
                messages.addError(new DuplicatedQuestionDeclarations(question));
            }
            if (questions.hasLabelDuplicates(question)) {
                messages.addError(new DuplicatedQuestionLabels(question));
            }
        }
    }

    public void checkStatements(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this, null);
        }
    }

    private void checkCondition(Expression condition) {
        Type conditionType = condition.accept(this, null);

        if (!conditionType.isBoolean()) {
            messages.addError(new TypeMismatch(new BooleanType(), conditionType));
        }
    }

    private void checkQuestionText(QuestionText questionText) {
        if (questionText.toString().isEmpty()) {
            messages.addError(new EmptyQuestionLabel(questionText));
        }
    }

    private void fillSymbolTable(List<Question> questions) {
        for (Question question : questions) {
            symbolTable.declare(question.getId(), question.getType());
        }
    }

    private void checkCircularDependencies() {
        DependencySet dependencies = circularDependenciesResolver.circularDependencies();

        for (DependencyPair pair : dependencies) {
            messages.addError(new CircularDependency(pair));
        }
    }
}
