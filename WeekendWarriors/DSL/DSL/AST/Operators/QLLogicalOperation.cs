using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    public class QLLogicalOperation : QLBinaryOperation
    {
        public QLLogicalOperation(IQLExpression lhs, QLBinaryOperator operation, IQLExpression rhs) : base(lhs, operation, rhs)
        {
        }
      
        public override QLType? GetQLType()
        {
            // Logical operation requires two operands of type boolean
            var lhsType = Lhs.GetQLType();
            var rhsType = Rhs.GetQLType();

            if (lhsType == QLType.Bool && rhsType == QLType.Bool)
                return QLType.Bool;

            return null;
        }

        public bool Validate()
        {
            var type = GetQLType();
            return type != null;
        }
        
    }
}
