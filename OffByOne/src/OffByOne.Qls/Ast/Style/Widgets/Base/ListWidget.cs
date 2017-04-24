namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Literals;

    public abstract class ListWidget : Widget
    {
        protected ListWidget(
            IList<StringLiteral> values)
        {
            if (values == null)
            {
                throw new ArgumentNullException(nameof(values), "A non-null option list must be given");
            }

            this.Values = values;
        }

        public IList<StringLiteral> Values { get; }
    }
}
