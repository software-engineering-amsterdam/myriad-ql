using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.AST
{
    public enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    public abstract class QLUnaryOperation : IQLExpression
    {
        public QLUnaryOperation(IQLExpression operand)
        {
            this.Operand = operand;
        }

        public IQLExpression Operand
        {
            get;
        }

        public abstract QLType? CheckOperandTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}
