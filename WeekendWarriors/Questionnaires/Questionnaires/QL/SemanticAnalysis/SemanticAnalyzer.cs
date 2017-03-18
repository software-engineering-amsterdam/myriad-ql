namespace Questionnaires.SemanticAnalysis
{
    public class SemanticAnalyzer
    {
        private Compilation.Result Result;

        public SemanticAnalyzer(Compilation.Result result)
        {
            Result = result;
        }

        public void AnalyzeForm(QL.AST.Form form)
        {
            AnalyzeAstNode(form);
        }

        protected void AnalyzeAstNode(QL.AST.INode node)
        {
            QLContext Context = new QLContext();

            // Get and check question declarations
            Result.Combine(new DeclarationValidator().Analyze(node, Context));
            if (!Result.ContainsErrors()) // Only apply type checking if the declaration validator passed
            {
                var TypeCheckerResult = new TypeChecker().Analyze(node, Context);
                Result.Combine(TypeCheckerResult);
            }
        }
    }
}
