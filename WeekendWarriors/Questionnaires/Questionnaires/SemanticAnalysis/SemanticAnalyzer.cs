using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
{
    public class SemanticAnalyzer
    {
        private List<Run.ISemanticAnalyzerRun> Runs = new List<Run.ISemanticAnalyzerRun>();
        private QLContext Context = new QLContext();
        private Run.Result AnalysisResult = new Run.Result();

        public SemanticAnalyzer()
        {
            Runs.Add(new Run.DeclarationValidator());
            Runs.Add(new Run.TypeChecker());
        }

        public Run.Result Analyze(AST.INode node)
        {
            foreach(var run in Runs)
            {
                var result = run.Analyze(node, Context);

                AnalysisResult.Combine(result);

                // Don't continue with the next run if we found an error
                if (result.IsError())
                    break;                
            }

            return AnalysisResult;
        }
    }
}
