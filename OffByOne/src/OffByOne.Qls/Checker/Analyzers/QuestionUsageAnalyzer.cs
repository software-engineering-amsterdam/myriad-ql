namespace OffByOne.Qls.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Common.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionUsageAnalyzer : BaseQlsVisitor<object, IQuestionUsageEnviorment>, IAnalyzer
    {
        public QuestionUsageAnalyzer()
            : this(new CheckerReport())
        {
        }

        public QuestionUsageAnalyzer(ICheckerReport report)
        {
            if (report == null)
            {
                throw new ArgumentNullException(nameof(report));
            }

            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> questionMappings)
        {
            if (root == null)
            {
                throw new ArgumentNullException(nameof(root));
            }

            if (questionMappings == null)
            {
                throw new ArgumentNullException(nameof(questionMappings));
            }

            var enviorment = new QuestionUsageEnviorment();

            questionMappings
                .ForEach(x => enviorment.AddQuestion(x.Key));
            this.Visit(root, enviorment);

            if (enviorment.HasUnusedQuestions)
            {
                enviorment.UnusedQuestions
                    .ForEach(x => this.Report.Add(new UnusedQuestionMessage(x)));
            }
        }

        public override object Visit(QuestionRule rule, IQuestionUsageEnviorment environment)
        {
            if (environment.HasQuestion(rule.Identifier))
            {
                environment.MarkQuestionAsUsed(rule.Identifier);
            }
            else
            {
                this.Report.Add(new UndeclaredQuestionMessage(rule.Identifier));
            }

            return base.Visit(rule, environment);
        }
    }
}
