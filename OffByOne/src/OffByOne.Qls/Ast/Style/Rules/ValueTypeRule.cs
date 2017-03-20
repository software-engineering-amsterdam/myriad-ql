namespace OffByOne.Qls.Ast.Style.Rules
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Rules.Base;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class ValueTypeRule : Rule
    {
        public ValueTypeRule(
            ValueType valueType,
            Widget widget,
            IEnumerable<Property> properties)
            : base(widget, properties)
        {
            if (valueType == null)
            {
                throw new ArgumentNullException(nameof(valueType));
            }

            this.ValueType = valueType;
        }

        public ValueType ValueType { get; }

        public override TResult Accept<TResult, TContext>(
            IRuleVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
