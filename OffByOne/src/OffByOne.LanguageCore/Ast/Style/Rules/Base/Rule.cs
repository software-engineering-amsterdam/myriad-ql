namespace OffByOne.LanguageCore.Ast.Style.Rules.Base
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Style.Properties.Base;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;

    public class Rule : AstNode
    {
        public Rule(
            Widget widget,
            ICollection<Property> properties)
        {
            this.Widget = widget;
            this.Properties = properties;
        }

        public Widget Widget { get; private set; }

        public ICollection<Property> Properties { get; private set; }
    }
}
