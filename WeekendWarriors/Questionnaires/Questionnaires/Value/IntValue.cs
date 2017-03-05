using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class IntValue : Value<int>
    {
        public IntValue()
        {
        }

        public IntValue(int value)
        {
            this.Val = value;
        }        

        public override IValue Positive()
        {
            return new IntValue(this.Val);
        }

        public override IValue Negative()
        {
            return new IntValue(-this.Val);
        }

        public override IValue Add(IntValue value)
        {
            return new IntValue(this.Val + value.GetValue());
        }

        public override IValue Add(DecimalValue value)
        {
            return new DecimalValue(this.Val + value.GetValue());
        }

        public override IValue Subtract(IntValue value)
        {
            return new IntValue(this.Val - value.GetValue());
        }

        public override IValue Subtract(DecimalValue value)
        {
            return new DecimalValue(this.Val - value.GetValue());
        }

        public override IValue Multiply(IntValue value)
        {
            return new IntValue(this.Val * value.GetValue());
        }

        public override IValue Multiply(DecimalValue value)
        {
            return new DecimalValue(this.Val * value.GetValue());
        }

        public override IValue Divide(IntValue value)
        {
            return new IntValue(this.Val / value.GetValue());
        }

        public override IValue Divide(DecimalValue value)
        {
            return new DecimalValue(this.Val / value.GetValue());
        }

        public override BoolValue LessThan(IntValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public override BoolValue LessThan(DecimalValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public override BoolValue LessThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public override BoolValue LessThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public override BoolValue GreaterThan(IntValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public override BoolValue GreaterThan(DecimalValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public override BoolValue GreaterThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public override BoolValue GreaterThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public override BoolValue EqualTo(IntValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override BoolValue EqualTo(DecimalValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override BoolValue InequalTo(IntValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }

        public override BoolValue InequalTo(DecimalValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }
    }
}
