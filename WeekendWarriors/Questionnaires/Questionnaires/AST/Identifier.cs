using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class Identifier : IExpression
    {
        public Identifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events)
        {
            Trace.Assert(parameters.Count == 0);
            
            if(!context.ContainsQuestion(Name))
            {
                events.Add(new Error(string.Format("Invalid use of undefined identifier {0}", Name)));
                return null;
            }

            return context.GetQuestionType(Name);
        }
    }
}
