using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents
{
    public class SemanticAnalysisWarning : SemanticAnalysisEvent
    {
        public SemanticAnalysisWarning(string message) : base("Warning: " + message)
        {
           
        }

        public override bool IsError()
        {
            return false;
        }
    }
}
