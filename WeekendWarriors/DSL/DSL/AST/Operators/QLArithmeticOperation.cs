using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    class QLArithmeticOperation : QLBinaryOperation
    {
        public QLArithmeticOperation(INode lhs, QLBinaryOperator operation, INode rhs) : base(lhs, operation, rhs)
        {

        }
    }
}
