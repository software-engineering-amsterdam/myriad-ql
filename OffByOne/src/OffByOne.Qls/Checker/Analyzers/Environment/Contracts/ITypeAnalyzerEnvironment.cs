namespace OffByOne.Qls.Checker.Analyzers.Environment.Contracts
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public interface ITypeAnalyzerEnvironment : IEnvironment
    {
        IEnumerable<ValueType> GetCorrectTypesForWidget(Widget widget);

        bool IsWidgetTypingCorrect(Widget widget, ValueType questionType);
    }
}