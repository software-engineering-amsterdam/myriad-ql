namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;

    public class BracketExpression : Expression
    {
        public BracketExpression(Expression expression)
        {
            this.Expression = expression;
        }

        public Expression Expression { get; private set; }
    }
}
