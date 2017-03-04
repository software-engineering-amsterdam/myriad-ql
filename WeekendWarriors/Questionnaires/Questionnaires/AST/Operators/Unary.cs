using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;

namespace Questionnaires.AST
{
    public enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    public abstract class Unary : IExpression
    {
        public Unary(IExpression operand)
        {
            this.Operand = operand;
        }

        public IExpression Operand
        {
            get;
        }

        public abstract QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events);
    }
}
