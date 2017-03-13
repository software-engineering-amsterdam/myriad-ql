namespace OffByOne.Runner.Builder.Contracts
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Statements;

    public interface IQlLanguageBuilder
    {
        FormStatement BuildAst();

        void CheckSyntax(FormStatement root);

        void RunApplication(FormStatement structureNode);
    }
}
