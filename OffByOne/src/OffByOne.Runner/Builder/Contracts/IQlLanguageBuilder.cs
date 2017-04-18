namespace OffByOne.Runner.Builder.Contracts
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Contracts;

    public interface IQlLanguageBuilder
    {
        FormStatement BuildAst();

        ICheckerReport CheckSyntax(FormStatement root);

        void RunApplication(FormStatement structureNode);
    }
}
