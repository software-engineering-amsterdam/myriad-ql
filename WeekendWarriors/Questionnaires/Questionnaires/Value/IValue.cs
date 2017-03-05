using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public interface IValue
    {
        // Unary operations
        IValue Positive();
        IValue Negative();
        BoolValue Bang();

        // Binary operations
        IValue Add(IValue value);
        IValue Subtract(IValue value);
        IValue Multiply(IValue value);
        IValue Divide(IValue value);
        BoolValue And(IValue value);
        BoolValue Or(IValue value);
        BoolValue LessThan(IValue value);
        BoolValue LessThanOrEqual(IValue value);
        BoolValue GreaterThan(IValue value);
        BoolValue GreaterThanOrEqual(IValue value);
        BoolValue EqualTo(IValue value);
        BoolValue InequalTo(IValue value);
    }
}
