using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    public class QLEqualityOperation : QLBinaryOperation
    {
        public QLEqualityOperation(IQLExpression lhs, QLBinaryOperator operation, IQLExpression rhs) : base(lhs, operation, rhs)
        {
        }

        public override QLType? GetQLType()
        {
            if (Lhs.GetQLType() == Rhs.GetQLType())
                return Lhs.GetQLType();

            return null;
        }
    }
}
