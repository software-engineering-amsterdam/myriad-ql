using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
{
    public class SemanticAnalyzer
    {
        private QLContext Context = new QLContext();

        public SemanticAnalyzer()
        {

        }

        public Run.Result AnalyzeForm(AST.Form form)
        {
            return AnalyzeAstNode(form);
        }

        protected Run.Result AnalyzeAstNode(AST.INode node)
        {
            // Get and check question declarations
            var Results = new Run.DeclarationValidator().Analyze(node, Context);
            if (!Results.IsError()) // Only apply type checking if the declaration validator passed
            {
                var TypeCheckerResult = new Run.TypeChecker().Analyze(node, Context);
                Results.Combine(TypeCheckerResult);
            }
            
            return Results;
        }
    }
}
