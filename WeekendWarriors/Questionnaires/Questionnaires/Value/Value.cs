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

        public virtual int AsInt()
        {
            throw new NotSupportedException();
        }

        public virtual decimal AsDecimal()
        {
            throw new NotSupportedException();
        }

        public virtual bool AsBool()
        {
            throw new NotSupportedException();
        }

        public virtual String AsString()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Positive()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Negative()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Bang()
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

        public virtual IValue And(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Or(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThan(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThanOrEqual(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThan(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThanOrEqual(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue EqualTo(StringValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue InequalTo(StringValue value)
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

        public virtual IValue And(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Or(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThan(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThanOrEqual(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThan(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThanOrEqual(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue EqualTo(IntValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue InequalTo(IntValue value)
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

        public virtual IValue And(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Or(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThan(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThanOrEqual(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThan(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThanOrEqual(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue EqualTo(DecimalValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue InequalTo(DecimalValue value)
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

        public virtual IValue And(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Or(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThan(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThanOrEqual(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThan(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThanOrEqual(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue EqualTo(BoolValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue InequalTo(BoolValue value)
        {
            throw new NotSupportedException();
        }
    }
}
