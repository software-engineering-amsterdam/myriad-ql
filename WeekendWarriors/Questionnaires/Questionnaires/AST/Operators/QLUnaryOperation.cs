using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    public class QLUnaryOperation : INode
    {
        public QLUnaryOperation(INode operand, QLUnaryOperator op)
        {
            this.Operand = operand;
            this.Operator = op;
        }

        public QLUnaryOperator Operator
        {
            get;
        }

        public INode Operand
        {
            get;
        }
    }
}
