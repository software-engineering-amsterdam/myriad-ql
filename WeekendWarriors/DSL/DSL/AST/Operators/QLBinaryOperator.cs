using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLBinaryOperator : INode
    {
        public QLBinaryOperator(INode lhs, string op, INode rhs)
        {
            this.Lhs = lhs;
            this.Operator = op;
            this.Rhs = rhs;
        }

        public string Operator
        {
            get;
        }

        public INode Lhs
        {
            get;
        }

        public INode Rhs
        {
            get;
        }
    }
}
