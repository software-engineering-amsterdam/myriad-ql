using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST.Operators
{
    public class QLArithmeticOperation : QLBinaryOperation
    {
        public QLArithmeticOperation(IQLExpression lhs, QLBinaryOperator operation, IQLExpression rhs) : base(lhs, operation, rhs)
        {
                        
        }

        public override QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 2);
            var leftHandSideType = parameters[0];
            var rightHandsSideType = parameters[1];

            var validTypes = new List<QLType> { QLType.Number, QLType.Money };
            if (!(validTypes.Contains(leftHandSideType) && validTypes.Contains(rightHandsSideType)))
            {
                events.Add(new SemanticAnalysisError(string.Format("Cannot apply operator {0} on arguments of type {1} and {2}", this.Operator, leftHandSideType, rightHandsSideType)));
                return null;
            }

            if (leftHandSideType == QLType.Money || rightHandsSideType == QLType.Money)
                return QLType.Money;
            else
                return QLType.Number;
        }
    }
}
