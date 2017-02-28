namespace OffByOne.Qls.Ast.Style.Rules.Base
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Rule : AstNode, IVisitibleRule
    {
        protected Rule(
            Widget widget,
            IEnumerable<Property> properties)
        {
            this.Widget = widget;
            this.Properties = properties;
        }

        public Widget Widget { get; private set; }

        public IEnumerable<Property> Properties { get; private set; }

        public abstract TResult Accept<TResult, TContext>(IRuleVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
