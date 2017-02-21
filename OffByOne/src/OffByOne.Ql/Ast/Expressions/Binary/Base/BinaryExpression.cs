namespace OffByOne.Ql.Ast.Expressions.Binary.Base
{
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class BinaryExpression : Expression
    {
        protected BinaryExpression(
            Expression leftExpression,
            Expression rightExpression)
        {
            this.LeftExpression = leftExpression;
            this.RightExpression = rightExpression;
        }

        public Expression LeftExpression { get; private set; }

        public Expression RightExpression { get; private set; }
    }
}
