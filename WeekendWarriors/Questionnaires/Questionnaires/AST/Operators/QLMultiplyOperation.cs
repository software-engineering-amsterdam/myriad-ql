using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class QLMultiplyOperation : QLArithmeticOperation
    {
        public QLMultiplyOperation(IQLExpression lhs, IQLExpression rhs) : base(lhs, QLBinaryOperator.Multiplication, rhs)
        {
        }
    }
}
