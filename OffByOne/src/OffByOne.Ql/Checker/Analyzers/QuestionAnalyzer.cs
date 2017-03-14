namespace OffByOne.Ql.Checker.Analyzers
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Visitors.Base;

    public class QuestionAnalyzer : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>, IAnalyzer
    {
        public QuestionAnalyzer(ICheckerReport report)
        {
            this.Report = report;
        }

        public QuestionAnalyzer()
            : this(new CheckerReport())
        {
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
            this.Visit(root, new QuestionVisitorTypeEnvironment());
        }

        public override object Visit(QuestionStatement statement, QuestionVisitorTypeEnvironment environment)
        {
            // TODO: change string primitives to StringValue?
            // [...].Value.Value is ugly. Since StringValues replace string primitives,
            // maybe we should replace them in the code too?
            if (environment.IsNameDuplicate(statement.Label.Value.Value))
            {
                this.Report.Add(new DuplicateQuestionIdentifierMessage(statement));
            }
            else
            {
                environment.AddQuestionName(statement.Label.Value.Value);
            }

            if (environment.IsLableDuplicate(statement.Identifier))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(statement));
            }
            else
            {
                environment.AddQuestionLabel(statement.Identifier);
            }

            return base.Visit(statement, environment);
        }
    }
}
