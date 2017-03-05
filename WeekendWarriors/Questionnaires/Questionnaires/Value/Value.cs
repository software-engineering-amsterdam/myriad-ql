using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public abstract class Value<T> : IValue
    {
        protected T Val = default(T);

        public T GetValue()
        {
            return Val;
        }

        public virtual IValue Positive()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Negative()
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue Bang()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Add(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Subtract(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Multiply(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Divide(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue And(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue Or(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThan(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThanOrEqual(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThan(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThanOrEqual(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue EqualTo(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue InequalTo(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Add(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Subtract(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Multiply(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Divide(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue And(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue Or(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThan(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThanOrEqual(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThan(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThanOrEqual(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue EqualTo(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue InequalTo(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Add(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Subtract(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Multiply(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Divide(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue And(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue Or(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThan(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThanOrEqual(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThan(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThanOrEqual(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue EqualTo(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue InequalTo(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Add(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Subtract(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Multiply(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Divide(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue And(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue Or(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThan(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue LessThanOrEqual(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThan(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue GreaterThanOrEqual(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue EqualTo(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual BoolValue InequalTo(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public IValue Add(IValue value)
        {
            return Add((dynamic)value);
        }

        public IValue Subtract(IValue value)
        {
            return Subtract((dynamic)value);
        }

        public IValue Multiply(IValue value)
        {
            return Multiply((dynamic)value);
        }

        public IValue Divide(IValue value)
        {
            return Divide((dynamic)value);
        }

        public BoolValue And(IValue value)
        {
            return And((dynamic)value);
        }

        public BoolValue Or(IValue value)
        {
            return Or((dynamic)value);
        }

        public BoolValue LessThan(IValue value)
        {
            return LessThan((dynamic)value);
        }

        public BoolValue LessThanOrEqual(IValue value)
        {
            return LessThanOrEqual((dynamic)value);
        }

        public BoolValue GreaterThan(IValue value)
        {
            return GreaterThan((dynamic)value);
        }

        public BoolValue GreaterThanOrEqual(IValue value)
        {
            return GreaterThanOrEqual((dynamic)value);
        }

        public BoolValue EqualTo(IValue value)
        {
            return EqualTo((dynamic)value);
        }

        public BoolValue InequalTo(IValue value)
        {
            return InequalTo((dynamic)value);
        }
    }
}
