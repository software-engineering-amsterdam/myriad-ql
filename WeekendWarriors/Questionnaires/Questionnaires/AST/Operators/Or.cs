using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST.Operators
{
    public class Or : Logical
    {
        public Or(IExpression lhs, IExpression rhs) : base(lhs, QLBinaryOperator.Or, rhs)
        {
        }
    }
}
