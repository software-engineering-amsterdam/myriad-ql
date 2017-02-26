using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLBoolean : IQLExpression
    {
        public QLBoolean(bool value)
        {
            this.Value = value;
        }

        public bool Value
        {
            get;
        }

        public QLType? CheckOperandTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 0);
            return QLType.Bool;
        }        
    }
}
