namespace OffByOne.Ql.Checker.Analyzers
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Contracts;

    public class CyclicDependencyAnalyzer : IAnalyzer
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
    }
}
