using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis.SemenaticAnalysisEvents
{
    public class SemanticAnalysisWarning : SemanticAnalysisEvent
    {
        public SemanticAnalysisWarning(string message) : base("Warning: " + message)
        {
           
        }
    }
}
