namespace OffByOne.Ql.Checker.Analyzers
{
    using System;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Analyzers.Environment.Contracts;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Common.Visitors.Base;

    public class QuestionAnalyzer : BaseQlVisitor<object, IQuestionEnvironment>, IAnalyzer
    {
        public QuestionAnalyzer()
            : this(new CheckerReport())
        {
        }

        public QuestionAnalyzer(ICheckerReport report)
        {
            if (report == null)
            {
                throw new ArgumentNullException(nameof(report));
            }

            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
            this.Visit(root, new QuestionEnvironment());
        }

        public override object Visit(QuestionStatement statement, IQuestionEnvironment environment)
        {
            // TODO: change string primitives to StringValue?
            // [...].Value.Value is ugly. Since StringValues replace string primitives,
            // maybe we should replace them in the code too?
            if (environment.IsIdentifierDuplicate(statement.Identifier))
            {
                this.Report.Add(new DuplicateQuestionIdentifierMessage(statement));
            }
            else
            {
                environment.AddQuestionIdentifier(statement.Identifier);
            }

            if (environment.IsLableDuplicate(statement.Label.Value.Value))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(statement));
            }
            else
            {
                environment.AddQuestionLabel(statement.Label.Value.Value);
            }

            return base.Visit(statement, environment);
        }
    }
}
