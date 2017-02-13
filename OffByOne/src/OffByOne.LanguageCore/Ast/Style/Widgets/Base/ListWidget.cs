namespace OffByOne.LanguageCore.Ast.Style.Widgets.Base
{
    using System.Collections.Generic;

    public abstract class ListWidget : Widget
    {
        protected ListWidget(
            string defaultValue,
            ICollection<string> values)
        {
            this.DefaultValue = defaultValue;
            this.Values = values;
        }

        public string DefaultValue { get; set; }

        public ICollection<string> Values { get; set; }
    }
}
