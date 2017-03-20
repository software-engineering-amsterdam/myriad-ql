namespace OffByOne.Ql.Ast.Expressions.Unary.Base
{
    using System;
    using System.Collections.Generic;

    public abstract class UnaryExpression : Expression
    {
        protected UnaryExpression(Expression expression)
        {
            if (expression == null)
            {
                throw new ArgumentNullException(nameof(expression));
            }

            this.Expression = expression;
        }

        public Expression Expression { get; }

        public override ISet<string> GetDependencies()
        {
            return this.Expression.GetDependencies();
        }
    }
}
