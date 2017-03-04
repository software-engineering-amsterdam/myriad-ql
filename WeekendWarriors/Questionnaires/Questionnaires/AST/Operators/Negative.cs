using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;

namespace Questionnaires.AST.Operators
{
    public class Negative : Unary
    {
        public Negative(IExpression operand) : base(operand)
        {

        }

        public override QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events)
        {
            Trace.Assert(parameters.Count == 1);

            var operandType = parameters[0];

            // The operator accepts number and money. The result type is equal to the operand type
            if (operandType == QLType.Number || operandType == QLType.Money)
                return operandType;

            events.Add(new Error(string.Format("Cannot apply minus operator on type {0}", operandType)));
            return null;
        }
    }
}
