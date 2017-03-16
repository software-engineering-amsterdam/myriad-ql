namespace OffByOne.Runner.Builder
{
    using OffByOne.Runner.Builder.Contracts;

    public class AppRunner
    {
        public void Run(IQlLanguageBuilder qlLanguageBuilder)
        {
            var astTree = qlLanguageBuilder.BuildAst();
            var report = qlLanguageBuilder.CheckSyntax(astTree);
            if (!report.HasErrors())
            {
                qlLanguageBuilder.RunApplication(astTree);
            }
        }

        public void Run(IQlLanguageBuilder qlLanguageBuilder, IQlsLanguageBuilder qlsLanguageBuilder)
        {
            var qlAstTree = qlLanguageBuilder.BuildAst();
            qlLanguageBuilder.CheckSyntax(qlAstTree);

            var qlsAstTree = qlsLanguageBuilder.BuildAst();
            qlsLanguageBuilder.CheckSyntax(qlAstTree, qlsAstTree);
            qlsLanguageBuilder.RunApplication(qlAstTree, qlsAstTree);
        }
    }
}
