namespace OffByOne.Ql.Visitors.Base
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Visitors.Contracts;

    public class BaseQlVisitor<TResult, TContext>
        : IValueTypeVisitor<TResult, TContext>,
        IExpressionVisitor<TResult, TContext>,
        IStatementVisitor<TResult, TContext>
        where TContext : IContext
    {
        public virtual TResult Visit(IntegerLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(HexLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(IntegerValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(VoidValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(AddExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(SubtractExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(MultiplyExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(DivideExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(AndExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(OrExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(EqualExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(NotEqualExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(GreaterThanExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(GreaterThanOrEqualExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(LessThanExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(LessThanOrEqualExpression expression, TContext context)
        {
            return this.Visit((BinaryExpression)expression, context);
        }

        public virtual TResult Visit(NotExpression expression, TContext context)
        {
            return this.Visit((UnaryExpression)expression, context);
        }

        public virtual TResult Visit(NegativeExpression expression, TContext context)
        {
            return this.Visit((UnaryExpression)expression, context);
        }

        public virtual TResult Visit(PositiveExpression expression, TContext context)
        {
            return this.Visit((UnaryExpression)expression, context);
        }

        public virtual TResult Visit(VariableExpression expression, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BracketExpression expression, TContext context)
        {
            expression.Expression.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionStatement expression, TContext context)
        {
            expression.Type.Accept(this, context);
            expression.ComputationExpression?.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(IfStatement expression, TContext context)
        {
            expression.Condition.Accept(this, context);
            expression.Statements.ForEach(x => x.Accept(this, context));
            expression.ElseStatements.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(FormStatement expression, TContext context)
        {
            expression.Statements.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public ValueType Visit(IntegerLiteral literal, VisitorTypeEnvironment context)
        {
            return new IntegerValueType();
        }

        public ValueType Visit(MoneyLiteral literal, VisitorTypeEnvironment context)
        {
            return new MoneyValueType();
        }

        public ValueType Visit(DecimalLiteral literal, VisitorTypeEnvironment context)
        {
            return new DecimalValueType();
        }

        public ValueType Visit(BooleanLiteral literal, VisitorTypeEnvironment context)
        {
            return new BooleanValueType();
        }

        public ValueType Visit(StringLiteral literal, VisitorTypeEnvironment context)
        {
            return new StringValueType();
        }

        public ValueType Visit(DateLiteral literal, VisitorTypeEnvironment context)
        {
            return new DateValueType();
        }

        public ValueType Visit(HexLiteral literal, VisitorTypeEnvironment context)
        {
            return new StringValueType();
        }

        private TResult Visit(BinaryExpression expression, TContext context)
        {
            expression.RightExpression.Accept(this, context);
            expression.LeftExpression.Accept(this, context);

            return default(TResult);
        }

        private TResult Visit(UnaryExpression expression, TContext context)
        {
            expression.Expression.Accept(this, context);
            return default(TResult);
        }
    }
}
