using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace DSL.AST.Operators
{
    class QLNegativeOperation : QLUnaryOperation
    {
        public QLNegativeOperation(IQLExpression operand) : base(operand)
        {

        }

        public override QLType? CheckTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 1);

            var operandType = parameters[0];

            // The operator accepts number and money. The result type is equal to the operand type
            if (operandType == QLType.Number || operandType == QLType.Money)
                return operandType;

            events.Add(new SemanticAnalysisError(string.Format("Cannot apply minus operator on type {0}", operandType)));
            return null;
        }
    }
}
