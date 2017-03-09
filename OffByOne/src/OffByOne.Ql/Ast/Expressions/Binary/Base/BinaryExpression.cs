namespace OffByOne.Ql.Ast.Expressions.Binary.Base
{
    using System;
    using System.Collections.Generic;
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

        public override ISet<string> GetDependencies()
        {
            ISet<string> identifiers = new SortedSet<string>();
            identifiers.UnionWith(this.LeftExpression.GetDependencies());
            identifiers.UnionWith(this.RightExpression.GetDependencies());
            return identifiers;
        }
    }
}
