using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    class QLUnaryOperation : INode
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
