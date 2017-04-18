namespace OffByOne.Qls.Checker.Messages
{
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public class InvalidWidgetTypeMessage : ErrorMessage
    {
        public InvalidWidgetTypeMessage(
            Widget widget,
            ValueType expected,
            ValueType actual)
            : base($"Ivalid widget value type at {widget.SourceCode}. Expected {expected}, got {actual}")
        {
        }

        public InvalidWidgetTypeMessage(
            Widget widget,
            IEnumerable<ValueType> expected,
            ValueType actual)
            : base($"Ivalid widget value type at {widget.SourceCode}. Expected {expected.ToString(x => x.ToString(), ",")}, got {actual}")
        {
        }
    }
}
