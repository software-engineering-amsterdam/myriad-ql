namespace OffByOne.Ql.Visitors
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Visitors.Contracts;

    public class BaseQlVisitor<TResult>
        : ILiteralVisitor<TResult>,
        IValueTypeVisitor<TResult>,
        IExpressionVisitor<TResult>,
        IStatementVisitor<TResult>
    {
        public virtual TResult Visit(IntegerLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(HexLiteral literal)
        {
            return default(TResult);
        }

        public virtual TResult Visit(IntegerValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(FloatValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(VoidValueType valueType)
        {
            return default(TResult);
        }

        public virtual TResult Visit(AddExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(SubtractExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(MultiplyExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(DivideExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(AndExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(OrExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(EqualExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(NotEqualExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(GreaterThanExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(GreaterThanOrEqualExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(LessThanExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(LessThanOrEqualExpression expression)
        {
            return this.Visit((BinaryExpression)expression);
        }

        public virtual TResult Visit(NotExpression expression)
        {
            return this.Visit((UnaryExpression)expression);
        }

        public virtual TResult Visit(NegativeExpression expression)
        {
            return this.Visit((UnaryExpression)expression);
        }

        public virtual TResult Visit(PositiveExpression expression)
        {
            return this.Visit((UnaryExpression)expression);
        }

        public virtual TResult Visit(VariableExpression expression)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BracketExpression expression)
        {
            expression.Expression.Accept(this);
            return default(TResult);
        }

        public virtual TResult Visit(LiteralExpression expression)
        {
            expression.Literal.Accept(this);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionStatement expression)
        {
            expression.Type.Accept(this);
            expression.ComputedValue.Accept(this);
            expression.Question.Accept(this);
            return default(TResult);
        }

        public virtual TResult Visit(IfStatement expression)
        {
            expression.Condition.Accept(this);
            expression.Statements.ForEach(x => x.Accept(this));
            expression.ElseStatements.ForEach(x => x.Accept(this));
            return default(TResult);
        }

        public virtual TResult Visit(FormStatement expression)
        {
            expression.Statements.ForEach(x => x.Accept(this));
            return default(TResult);
        }

        private TResult Visit(BinaryExpression expression)
        {
            expression.RightExpression.Accept(this);
            expression.LeftExpression.Accept(this);

            return default(TResult);
        }

        private TResult Visit(UnaryExpression expression)
        {
            expression.Expression.Accept(this);
            return default(TResult);
        }
    }
}
