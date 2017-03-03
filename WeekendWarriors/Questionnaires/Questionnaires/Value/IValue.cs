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
        IValue Add(StringValue value);
        IValue Subtract(StringValue value);
        IValue Multiply(StringValue value);
        IValue Divide(StringValue value);
        IValue And(StringValue value);
        IValue Or(StringValue value);
        IValue LessThan(StringValue value);
        IValue LessThanOrEqual(StringValue value);
        IValue GreaterThan(StringValue value);
        IValue GreaterThanOrEqual(StringValue value);
        IValue EqualTo(StringValue value);
        IValue InequalTo(StringValue value);

        IValue Add(IntValue value);
        IValue Subtract(IntValue value);
        IValue Multiply(IntValue value);
        IValue Divide(IntValue value);
        IValue And(IntValue value);
        IValue Or(IntValue value);
        IValue LessThan(IntValue value);
        IValue LessThanOrEqual(IntValue value);
        IValue GreaterThan(IntValue value);
        IValue GreaterThanOrEqual(IntValue value);
        IValue EqualTo(IntValue value);
        IValue InequalTo(IntValue value);

        IValue Add(DecimalValue value);
        IValue Subtract(DecimalValue value);
        IValue Multiply(DecimalValue value);
        IValue Divide(DecimalValue value);
        IValue And(DecimalValue value);
        IValue Or(DecimalValue value);
        IValue LessThan(DecimalValue value);
        IValue LessThanOrEqual(DecimalValue value);
        IValue GreaterThan(DecimalValue value);
        IValue GreaterThanOrEqual(DecimalValue value);
        IValue EqualTo(DecimalValue value);
        IValue InequalTo(DecimalValue value);

        IValue Add(BoolValue value);
        IValue Subtract(BoolValue value);
        IValue Multiply(BoolValue value);
        IValue Divide(BoolValue value);
        IValue And(BoolValue value);
        IValue Or(BoolValue value);
        IValue LessThan(BoolValue value);
        IValue LessThanOrEqual(BoolValue value);
        IValue GreaterThan(BoolValue value);
        IValue GreaterThanOrEqual(BoolValue value);
        IValue EqualTo(BoolValue value);
        IValue InequalTo(BoolValue value);
    }
}
