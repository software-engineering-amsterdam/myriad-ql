using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class Subtraction : Arithmetic
    {
        public Subtraction(IExpression lhs, IExpression rhs) : base(lhs, QLBinaryOperator.Subtraction, rhs)
        {
        }
    }
}
