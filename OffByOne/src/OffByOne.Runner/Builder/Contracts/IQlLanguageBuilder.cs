namespace OffByOne.Runner.Builder.Contracts
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Interpreter.Widgets;

    public interface IQlLanguageBuilder
    {
        FormStatement BuildAst();

        ICheckerReport CheckSyntax(FormStatement root);

        FormWidget CreateFormWidget(FormStatement structureNode);

        void RunApplication(FormWidget structureNode);
    }
}
