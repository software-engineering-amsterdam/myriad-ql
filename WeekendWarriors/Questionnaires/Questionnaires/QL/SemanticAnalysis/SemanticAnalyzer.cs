using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
            var message = AnalyzeAstNode(form);
            Result.Combine(message);
        }

        protected Compilation.Result AnalyzeAstNode(QL.AST.INode node)
        {
            QLContext Context = new QLContext();

            // Get and check question declarations
            var Results = new DeclarationValidator().Analyze(node, Context);
            if (!Results.IsError()) // Only apply type checking if the declaration validator passed
            {
                var TypeCheckerResult = new TypeChecker().Analyze(node, Context);
                Results.Combine(TypeCheckerResult);
            }
            
            return Results;
        }
    }
}
