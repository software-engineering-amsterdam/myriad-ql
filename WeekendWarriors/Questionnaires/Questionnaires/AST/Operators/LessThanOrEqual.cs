using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class LessThanOrEqual : Comparison
    {
        public LessThanOrEqual(IExpression lhs, IExpression rhs) : base(lhs, QLBinaryOperator.LessThanOrEqual, rhs)
        {
        }
    }
}
