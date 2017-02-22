using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    public class QLComparisonOperation : QLBinaryOperation
    {
        public QLComparisonOperation(IQLExpression lhs, QLBinaryOperator operation, IQLExpression rhs) : base(lhs, operation, rhs)
        {
        }

        public override QLType? GetQLType()
        {
            var validTypes = new List<QLType> { QLType.Number, QLType.Money };

            var lhsType = Lhs.GetQLType();
            var rhsType = Rhs.GetQLType();

            if (!lhsType.HasValue || !rhsType.HasValue)
                return null;

            if (!validTypes.Contains(lhsType.Value))
                return null;

            if (!validTypes.Contains(rhsType.Value))
                return null;

            if (lhsType == QLType.Money || rhsType == QLType.Money)
                return QLType.Money;

            return QLType.Number;
        }

        public override bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            // Don't propagate errors up if we already encountered an error in the operand
            if (!Lhs.Validate(ref warnings, ref errors) || !Rhs.Validate(ref warnings, ref errors))
                return false;

            if (!GetQLType().HasValue)
            {
                errors.Add(string.Format("Cannot apply operator {0} on arguments of type {1} and {2}", this.Operator, Lhs.GetQLType(), Rhs.GetQLType()));
                return false;
            }

            return true;
        }
    }
}
