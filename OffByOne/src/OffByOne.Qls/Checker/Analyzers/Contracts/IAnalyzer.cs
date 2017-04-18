namespace OffByOne.Qls.Checker.Analyzers.Contracts
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;

    public interface IAnalyzer
    {
        ICheckerReport Report { get; }

        void Analyze(StyleSheet root, IDictionary<string, ValueType> qlQuestionMappings);
    }
}
