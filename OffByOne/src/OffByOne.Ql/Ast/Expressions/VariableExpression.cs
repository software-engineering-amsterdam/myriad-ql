namespace OffByOne.Ql.Ast.Expressions
{
    using System.Collections.Generic;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public class VariableExpression : Expression
    {
        public VariableExpression(string identifier)
        {
            this.Identifier = identifier;
        }

        public string Identifier { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }

        public override ISet<string> GetDependencies()
        {
            return new SortedSet<string>() { this.Identifier };
        }
    }
}
