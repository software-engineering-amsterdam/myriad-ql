using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST.Operators
{
    public class Logical : Binary
    {
        public Logical(IExpression lhs, QLBinaryOperator operation, IExpression rhs) : base(lhs, operation, rhs)
        {
        }

        public override QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 2);
            var leftHandSideType = parameters[0];
            var rightHandsSideType = parameters[1];

            if (!(leftHandSideType == QLType.Bool && rightHandsSideType == QLType.Bool))
            {
                events.Add(new SemanticAnalysisError(string.Format("Cannot apply operator {0} on arguments of type {1} and {2}", this.Operator, leftHandSideType, rightHandsSideType)));
                return null;
            }

            return QLType.Bool;
        }
    }
}
