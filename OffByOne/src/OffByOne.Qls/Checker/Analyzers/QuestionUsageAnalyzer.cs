namespace OffByOne.Qls.Checker.Analyzers
{
    using System.Collections.Generic;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionUsageAnalyzer : BaseQlsVisitor<object, FormAnalyzerEnvironment>, IAnalyzer
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

        public override object Visit(QuestionRule rule, FormAnalyzerEnvironment environment)
        {
            return null;
        }
    }
}
