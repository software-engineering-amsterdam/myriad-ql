using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLConditional : INode
    {
        public QLConditional(IQLExpression condition, List<INode> thenStatements, List<INode> elseStatements)
        {
            this.Condition = condition;
            this.ThenStatements = thenStatements;
            this.ElseStatements = elseStatements;
        }

        public IQLExpression Condition
        {
            get;
        }

        public List<INode> ThenStatements
        {
            get;
        }

        public List<INode> ElseStatements
        {
            get;
        }

        public QLType? CheckOperandTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 1);
            var conditionType = parameters[0];

            // We only accept conditions of boolean types (this is not C)
            if(conditionType != QLType.Bool)
            {
                events.Add(new SemanticAnalysisError("Condition for conditional statement cannot be resolved to boolean"));
            }

            // An if statement has no type to return
            return null;
        }
    }
}
