using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST.Operators
{
    public class Equality : Binary
    {
        public Equality(IExpression lhs, QLBinaryOperator operation, IExpression rhs) : base(lhs, operation, rhs)
        {
        }

        public override QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 2);
            var leftHandSideType = parameters[0];
            var rightHandsSideType = parameters[1];

            // We allow comparisson of all types. Only if both operand are of the same type
            if(leftHandSideType != rightHandsSideType)
            {
                events.Add(new SemanticAnalysisError(string.Format("Cannot apply operator {0} on arguments of type {1} and {2}", this.Operator, leftHandSideType, rightHandsSideType)));
                return null;
            }

            return QLType.Bool;
        }
    }
}
