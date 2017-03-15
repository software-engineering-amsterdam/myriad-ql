namespace OffByOne.Ql.Visitors.Base
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Visitors.Contracts;

    public class BaseQlVisitor<TResult, TEnvironment>
        : IValueTypeVisitor<TResult, TEnvironment>,
        IExpressionVisitor<TResult, TEnvironment>,
        IStatementVisitor<TResult, TEnvironment>
        where TEnvironment : IEnvironment
    {
        public TResult Visit(Expression expression, TEnvironment environment)
        {
            return expression.Accept(this, environment);
        }

        public virtual TResult Visit(IntegerLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(HexLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(IntegerValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(VoidValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(AddExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(SubtractExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(MultiplyExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(DivideExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(AndExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(OrExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(EqualExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(NotEqualExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(GreaterThanExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(GreaterThanOrEqualExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(LessThanExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(LessThanOrEqualExpression expression, TEnvironment environment)
        {
            return this.Visit((BinaryExpression)expression, environment);
        }

        public virtual TResult Visit(NotExpression expression, TEnvironment environment)
        {
            return this.Visit((UnaryExpression)expression, environment);
        }

        public virtual TResult Visit(NegativeExpression expression, TEnvironment environment)
        {
            return this.Visit((UnaryExpression)expression, environment);
        }

        public virtual TResult Visit(PositiveExpression expression, TEnvironment environment)
        {
            return this.Visit((UnaryExpression)expression, environment);
        }

        public virtual TResult Visit(VariableExpression expression, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BracketExpression expression, TEnvironment environment)
        {
            expression.Expression.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionStatement statement, TEnvironment environment)
        {
            statement.Type.Accept(this, environment);
            statement.ComputationExpression?.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(IfStatement statement, TEnvironment environment)
        {
            statement.Condition.Accept(this, environment);
            statement.Statements.ForEach(x => x.Accept(this, environment));
            statement.ElseStatements.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public TResult Visit(Statement statement, TEnvironment environment)
        {
            statement.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FormStatement statement, TEnvironment environment)
        {
            statement.Statements.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public ValueType Visit(IntegerLiteral literal, VisitorTypeEnvironment environment)
        {
            return new IntegerValueType();
        }

        public ValueType Visit(MoneyLiteral literal, VisitorTypeEnvironment environment)
        {
            return new MoneyValueType();
        }

        public ValueType Visit(DecimalLiteral literal, VisitorTypeEnvironment environment)
        {
            return new DecimalValueType();
        }

        public ValueType Visit(BooleanLiteral literal, VisitorTypeEnvironment environment)
        {
            return new BooleanValueType();
        }

        public ValueType Visit(StringLiteral literal, VisitorTypeEnvironment environment)
        {
            return new StringValueType();
        }

        public ValueType Visit(DateLiteral literal, VisitorTypeEnvironment environment)
        {
            return new DateValueType();
        }

        public ValueType Visit(HexLiteral literal, VisitorTypeEnvironment environment)
        {
            return new StringValueType();
        }

        private TResult Visit(BinaryExpression expression, TEnvironment environment)
        {
            expression.RightExpression.Accept(this, environment);
            expression.LeftExpression.Accept(this, environment);

            return default(TResult);
        }

        private TResult Visit(UnaryExpression expression, TEnvironment environment)
        {
            expression.Expression.Accept(this, environment);
            return default(TResult);
        }
    }
}
