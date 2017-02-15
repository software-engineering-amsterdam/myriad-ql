using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    class QLArithmaticOperation : QLBinaryOperator
    {
        public QLArithmaticOperation(INode lhs, QLOperator operation, INode rhs) : base(lhs, operation, rhs)
        {

        }
    }
}
