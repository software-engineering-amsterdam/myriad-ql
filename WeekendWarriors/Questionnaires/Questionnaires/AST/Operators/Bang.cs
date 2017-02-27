using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST.Operators
{
    public class Bang : Unary
    {
        public Bang(IExpression operand) : base(operand)
        {

        }

        public override QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Debug.Assert(parameters.Count == 1);

            var operandType = parameters[0];

            // The operator accepts number and money. The result type is equal to the operand type
            if (operandType == QLType.Bool)
                return operandType;

            events.Add(new SemanticAnalysisError(string.Format("Cannot apply bang operator on type {0}", operandType)));
            return null;
        }
    }
}
