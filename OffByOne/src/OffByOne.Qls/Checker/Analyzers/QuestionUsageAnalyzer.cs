namespace OffByOne.Qls.Checker.Analyzers
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionUsageAnalyzer : BaseQlsVisitor<object, FormAnalyzerEnvironment>, IAnalyzer
    {
        private IDictionary<string, bool> usageMap;

        public QuestionUsageAnalyzer()
            : this(new CheckerReport())
        {
        }

        public QuestionUsageAnalyzer(ICheckerReport report)
        {
            this.Report = report;
            this.usageMap = new Dictionary<string, bool>();
        }

        public ICheckerReport Report { get; }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> qlQuestionMappings)
        {
            this.usageMap = qlQuestionMappings
                .Select(x => x.Key)
                .ToDictionary(x => x, y => false);
            this.Visit(root, new FormAnalyzerEnvironment());

            var hasUnusedQuestions = this.usageMap.Any(x => !x.Value);
            if (hasUnusedQuestions)
            {
                var unusedQuestions = this.usageMap
                    .Where(x => !x.Value)
                    .Select(x => x.Key);

                unusedQuestions.ForEach(x => this.Report.Add(new UnusedQuestionMessage(x)));
            }
        }

        public override object Visit(QuestionRule rule, FormAnalyzerEnvironment environment)
        {
            if (this.usageMap.ContainsKey(rule.Identifier))
            {
                this.usageMap[rule.Identifier] = true;
            }
            else
            {
                this.Report.Add(new UndeclaredQuestionMessage(rule.Identifier));
            }

            return base.Visit(rule, environment);
        }
    }
}
