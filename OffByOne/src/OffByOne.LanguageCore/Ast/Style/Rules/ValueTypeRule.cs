namespace OffByOne.LanguageCore.Ast.Style.Rules
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Ast.Style.Properties.Base;
    using OffByOne.LanguageCore.Ast.Style.Rules.Base;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class ValueTypeRule : Rule
    {
        public ValueTypeRule(
            ValueType valueType,
            Widget widget,
            IEnumerable<Property> properties)
            : base(widget, properties)
        {
            this.ValueType = valueType;
        }

        public ValueType ValueType { get; private set; }
    }
}
