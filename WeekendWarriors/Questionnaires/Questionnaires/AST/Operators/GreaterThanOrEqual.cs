using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class GreaterThanOrEqual : Comparison
    {
        public GreaterThanOrEqual(IExpression lhs, IExpression rhs) : base(lhs, QLBinaryOperator.GreaterThanOrEqual, rhs)
        {
        }
    }
}
