namespace OffByOne.Ql.Checker.Analyzers
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Visitors.Base;

    public class CyclicDependencyAnalyzer : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>, IAnalyzer
    {
        public CyclicDependencyAnalyzer()
            : this(new CheckerReport())
        {
        }

        public CyclicDependencyAnalyzer(ICheckerReport report)
        {
            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
        }

        public override object Visit(QuestionStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            return base.Visit(expression, environment);
        }

        public override object Visit(IfStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            return base.Visit(expression, environment);
        }
    }
}
