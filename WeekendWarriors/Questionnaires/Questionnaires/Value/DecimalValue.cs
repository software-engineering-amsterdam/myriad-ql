using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class DecimalValue : Value<decimal>
    {
        public DecimalValue(decimal value)
        {
            this.Val = value;
        }

        public override decimal AsDecimal()
        {
            return this.Val;
        }

        public IValue Add(IntValue value)
        {
            return new DecimalValue(this.Val + value.AsInt());
        }

        public IValue Add(DecimalValue value)
        {
            return new DecimalValue(this.Val + value.AsDecimal());
        }

        public IValue Subtract(IntValue value)
        {
            return new DecimalValue(this.Val - value.AsInt());
        }

        public IValue Subtract(DecimalValue value)
        {
            return new DecimalValue(this.Val - value.AsDecimal());
        }

        public IValue Multiply(IntValue value)
        {
            return new DecimalValue(this.Val * value.AsInt());
        }

        public IValue Multiply(DecimalValue value)
        {
            return new DecimalValue(this.Val * value.AsDecimal());
        }

        public IValue Divide(IntValue value)
        {
            return new DecimalValue(this.Val / value.AsInt());
        }

        public IValue Divide(DecimalValue value)
        {
            return new DecimalValue(this.Val / value.AsDecimal());
        }

        public IValue LessThan(IntValue value)
        {
            return new BoolValue(this.Val < value.AsInt());
        }

        public IValue LessThan(DecimalValue value)
        {
            return new BoolValue(this.Val < value.AsDecimal());
        }

        public IValue LessThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val <= value.AsInt());
        }

        public IValue LessThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val <= value.AsDecimal());
        }

        public IValue GreaterThan(IntValue value)
        {
            return new BoolValue(this.Val > value.AsInt());
        }

        public IValue GreaterThan(DecimalValue value)
        {
            return new BoolValue(this.Val > value.AsDecimal());
        }

        public IValue GreaterThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val >= value.AsInt());
        }

        public IValue GreaterThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val >= value.AsDecimal());
        }

        public IValue EqualTo(IntValue value)
        {
            return new BoolValue(this.Val == value.AsInt());
        }

        public IValue EqualTo(DecimalValue value)
        {
            return new BoolValue(this.Val == value.AsDecimal());
        }

        public IValue InequalTo(IntValue value)
        {
            return new BoolValue(this.Val != value.AsInt());
        }

        public IValue InequalTo(DecimalValue value)
        {
            return new BoolValue(this.Val != value.AsDecimal());
        }
    }
}
