using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLUnaryOperator : INode
    {
        public QLUnaryOperator(INode operand, string op)
        {
            this.Operand = operand;
            this.Operator = op;
        }

        public string Operator
        {
            get;
        }

        public INode Operand
        {
            get;
        }
    }
}
