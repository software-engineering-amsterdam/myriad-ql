using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.Run
{
    public interface ISemanticAnalyzerRun
    {
        IResult Analyze(AST.INode node, QLContext context);
    }
}
