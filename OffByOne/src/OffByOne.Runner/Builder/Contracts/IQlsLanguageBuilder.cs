namespace OffByOne.Runner.Builder.Contracts
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Qls.Ast.Style.Statements;

    public interface IQlsLanguageBuilder
    {
        StyleSheet BuildAst();

        void CheckSyntax(FormStatement structureRoot, StyleSheet styleRoot);

        void RunApplication(FormStatement structureNode, StyleSheet styleNode);
    }
}
