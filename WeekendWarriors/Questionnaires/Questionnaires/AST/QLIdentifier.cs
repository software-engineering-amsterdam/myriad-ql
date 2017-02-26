using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLIdentifier : IQLExpression
    {
        public QLIdentifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 0);
            
            if(!context.ContainsQuestion(Name))
            {
                events.Add(new SemanticAnalysisError(string.Format("Invalid use of undefined identifier {0}", Name)));
                return null;
            }

            return context.GetQuestionType(Name);
        }
    }
}
