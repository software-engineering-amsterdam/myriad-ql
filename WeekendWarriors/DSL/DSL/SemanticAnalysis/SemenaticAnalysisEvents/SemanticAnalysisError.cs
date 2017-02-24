using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis.SemenaticAnalysisEvents
{
    class SemanticAnalysisError : SemanticAnalysisEvent
    {
        public SemanticAnalysisError(string message) : base("Warning: " + message)
        {

        }
    }
}
