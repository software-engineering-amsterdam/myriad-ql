using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class QLDivisionOperation : QLArithmeticOperation
    {
        public QLDivisionOperation(IQLExpression lhs, IQLExpression rhs) : base(lhs, QLBinaryOperator.Division, rhs)
        {
        }
    }
}
