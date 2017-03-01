using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class DecimalValue : Value<decimal>
    {
        public DecimalValue(decimal value)
        {
            this.Val = value;
        }

        public override decimal AsDecimal()
        {
            return this.Val;
        }

        public override IValue Add(IValue value)
        {
            return Add((dynamic)value);
        }

        public override IValue Subtract(IValue value)
        {
            return Subtract((dynamic)value);
        }

        public override IValue Multiply(IValue value)
        {
            return Multiply((dynamic)value);
        }

        public override IValue Divide(IValue value)
        {
            return Divide((dynamic)value);
        }        

        public override IValue LessThan(IValue value)
        {
            return LessThan((dynamic)value);
        }

        public override IValue LessThanOrEqual(IValue value)
        {
            return LessThanOrEqual((dynamic)value);
        }

        public override IValue GreaterThan(IValue value)
        {
            return GreaterThan((dynamic)value);
        }

        public override IValue GreaterThanOrEqual(IValue value)
        {
            return GreaterThanOrEqual((dynamic)value);
        }

        public override IValue EqualTo(IValue value)
        {
            return EqualTo((dynamic)value);
        }

        public override IValue InequalTo(IValue value)
        {
            return InequalTo((dynamic)value);
        }

        public IValue Add(IntValue value)
        {
            return new DecimalValue(this.Val + value.GetValue());
        }

        public IValue Add(DecimalValue value)
        {
            return new DecimalValue(this.Val + value.GetValue());
        }

        public IValue Subtract(IntValue value)
        {
            return new DecimalValue(this.Val - value.GetValue());
        }

        public IValue Subtract(DecimalValue value)
        {
            return new DecimalValue(this.Val - value.GetValue());
        }

        public IValue Multiply(IntValue value)
        {
            return new DecimalValue(this.Val * value.GetValue());
        }

        public IValue Multiply(DecimalValue value)
        {
            return new DecimalValue(this.Val * value.GetValue());
        }

        public IValue Divide(IntValue value)
        {
            return new DecimalValue(this.Val / value.GetValue());
        }

        public IValue Divide(DecimalValue value)
        {
            return new DecimalValue(this.Val / value.GetValue());
        }

        public IValue LessThan(IntValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public IValue LessThan(DecimalValue value)
        {
            return new BoolValue(this.Val < value.GetValue());
        }

        public IValue LessThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public IValue LessThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val <= value.GetValue());
        }

        public IValue GreaterThan(IntValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public IValue GreaterThan(DecimalValue value)
        {
            return new BoolValue(this.Val > value.GetValue());
        }

        public IValue GreaterThanOrEqual(IntValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public IValue GreaterThanOrEqual(DecimalValue value)
        {
            return new BoolValue(this.Val >= value.GetValue());
        }

        public IValue EqualTo(IntValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public IValue EqualTo(DecimalValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public IValue InequalTo(IntValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }

        public IValue InequalTo(DecimalValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }
    }
}
