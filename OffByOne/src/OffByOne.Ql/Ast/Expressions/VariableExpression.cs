namespace OffByOne.Ql.Ast.Expressions
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public class VariableExpression : Expression
    {
        public VariableExpression(string identifier)
        {
            if (identifier.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "A non-null, non-empty identifier must be given",
                    nameof(identifier));
            }

            this.Identifier = identifier;
        }

        public string Identifier { get; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }

        public override ISet<string> GetDependencies()
        {
            return new HashSet<string> { this.Identifier };
        }
    }
}
