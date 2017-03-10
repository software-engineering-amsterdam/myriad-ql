namespace OffByOne.Qls.Ast.Style.Rules
{
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Rules.Base;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class QuestionRule : Rule
    {
        public QuestionRule(
            string name,
            Widget widget,
            IEnumerable<Property> properties)
            : base(widget, properties)
        {
            this.Name = name;
        }

        public string Name { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IRuleVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
