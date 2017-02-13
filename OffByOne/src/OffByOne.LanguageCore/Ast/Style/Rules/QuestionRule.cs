namespace OffByOne.LanguageCore.Ast.Style.Rules
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Style.Properties.Base;
    using OffByOne.LanguageCore.Ast.Style.Rules.Base;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;

    public class QuestionRule : Rule
    {
        public QuestionRule(
            string name,
            Widget widget,
            ICollection<Property> properties)
            : base(widget, properties)
        {
            this.Name = name;
        }

        public string Name { get; private set; }
    }
}
