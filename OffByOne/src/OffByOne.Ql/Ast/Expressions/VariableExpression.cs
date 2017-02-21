namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class VariableExpression : Expression, IVisitableExpression
    {
        public VariableExpression(string identifier)
        {
            this.Identifier = identifier;
        }

        public string Identifier { get; private set; }

        public TResult Accept<TResult>(IExpressionVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
