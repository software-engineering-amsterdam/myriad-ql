namespace OffByOne.Ql.Checker.Analyzers.Contracts
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Contracts;

    public interface IAnalyzer
    {
        ICheckerReport Report { get; }

        void Analyze(FormStatement root);
    }
}
