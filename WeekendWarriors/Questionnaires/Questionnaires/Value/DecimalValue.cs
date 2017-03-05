using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class DecimalValue : Value<decimal>
    {
        public DecimalValue()
        {
        }

        public DecimalValue(decimal value)
        {
            this.Val = value;
        }

        public override decimal AsDecimal()
        {
            return this.Val;
        }

        public override IValue Positive()
        {
            return new DecimalValue(this.Val);
        }

        public override IValue Negative()
        {
            return new DecimalValue(-this.Val);
        }

        public override IValue Add(IntValue value)
        {
            return new DecimalValue(this.Val + value.GetValue());
        }

        public override IValue Add(DecimalValue value)
        {
            return new DecimalValue(this.Val + value.GetValue());
        }

        public override IValue Subtract(IntValue value)
        {
            return new DecimalValue(this.Val - value.GetValue());
        }

        public override IValue Subtract(DecimalValue value)
        {
            return new DecimalValue(this.Val - value.GetValue());
        }

        public override IValue Multiply(IntValue value)
        {
            return new DecimalValue(this.Val * value.GetValue());
        }

        public override IValue Multiply(DecimalValue value)
        {
            return new DecimalValue(this.Val * value.GetValue());
        }

        public override IValue Divide(IntValue value)
        {
            return new DecimalValue(this.Val / value.GetValue());
        }

        public override IValue Divide(DecimalValue value)
        {
            return new DecimalValue(this.Val / value.GetValue());
        }

        public override IValue LessThan(IntValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public override IValue LessThan(DecimalValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public override IValue LessThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public override IValue LessThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public override IValue GreaterThan(IntValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public override IValue GreaterThan(DecimalValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public override IValue GreaterThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public override IValue GreaterThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public override IValue EqualTo(IntValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override IValue EqualTo(DecimalValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override IValue InequalTo(IntValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }

        public override IValue InequalTo(DecimalValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }
    }
}
