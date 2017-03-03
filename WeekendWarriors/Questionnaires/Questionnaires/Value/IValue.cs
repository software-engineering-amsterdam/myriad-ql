using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public interface IValue
    {
        int AsInt();
        decimal AsDecimal();
        bool AsBool();
        String AsString();

        // Unary operations
        IValue Positive();
        IValue Negative();
        IValue Bang();

        // Binary operations
        IValue Add(IValue value);
        IValue Subtract(IValue value);
        IValue Multiply(IValue value);
        IValue Divide(IValue value);
        IValue And(IValue value);
        IValue Or(IValue value);
        IValue LessThan(IValue value);
        IValue LessThanOrEqual(IValue value);
        IValue GreaterThan(IValue value);
        IValue GreaterThanOrEqual(IValue value);
        IValue EqualTo(IValue value);
        IValue InequalTo(IValue value);
    }
}
