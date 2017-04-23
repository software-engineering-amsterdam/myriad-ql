namespace OffByOne.Ql.Ast.Expressions.Binary.Base
{
    using System;
    using System.Collections.Generic;

    public abstract class BinaryExpression : Expression
    {
        protected BinaryExpression(
            Expression leftExpression,
            Expression rightExpression)
        {
            if (leftExpression == null)
            {
                throw new ArgumentNullException(nameof(leftExpression));
            }

            if (rightExpression == null)
            {
                throw new ArgumentNullException(nameof(rightExpression));
            }

            this.LeftExpression = leftExpression;
            this.RightExpression = rightExpression;
        }

        public Expression LeftExpression { get; }

        public Expression RightExpression { get; }

        public override ISet<string> GetDependencies()
        {
            var identifiers = new HashSet<string>();
            identifiers.UnionWith(this.LeftExpression.GetDependencies());
            identifiers.UnionWith(this.RightExpression.GetDependencies());
            return identifiers;
        }
    }
}
