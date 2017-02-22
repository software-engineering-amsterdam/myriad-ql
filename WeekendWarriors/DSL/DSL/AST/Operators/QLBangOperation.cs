using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST.Operators
{
    class QLBangOperation : QLUnaryOperation
    {
        public QLBangOperation(IQLExpression operand) : base(operand)
        {

        }

        public override bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            // Don't propagate errors up if we already encountered an error in the operand
            if (!Operand.Validate(ref warnings, ref errors))
                return false;

            // Positive operation can only be applied to numbers and decimals
            if (Operand.GetQLType() == QLType.Bool)
                return true;

            errors.Add(string.Format("Cannot apply bang operator on type {0}", Operand.GetQLType()));
            return false;
        }
    }
}
