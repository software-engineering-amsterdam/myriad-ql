using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    public abstract class QLUnaryOperation : IQLExpression
    {
        public QLUnaryOperation(IQLExpression operand)
        {
            this.Operand = operand;
        }

        public IQLExpression Operand
        {
            get;
        }

        public abstract bool Validate(ref List<string> warnings, ref List<string> errors);

        QLType? IQLExpression.GetQLType()
        {
            // The unary operation alwyas has the same type as its operand.
            // Even if the operand is null
            return Operand.GetQLType();
        }        
    }
}
