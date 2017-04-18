namespace OffByOne.Qls.Checker.Analyzers
{
    using System.Collections.Generic;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionUsageAnalyzer : IAnalyzer
    {
        public QuestionUsageAnalyzer()
            : this(new CheckerReport())
        {
        }

        public QuestionUsageAnalyzer(ICheckerReport report)
        {
            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> qlQuestionMappings)
        {
        }
    }
}
