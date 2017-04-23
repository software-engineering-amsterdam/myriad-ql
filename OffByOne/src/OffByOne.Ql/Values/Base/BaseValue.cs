namespace OffByOne.Ql.Values.Base
{
    using System;

    using OffByOne.Ql.Values.Contracts;

    public abstract class BaseValue : IValue
    {
        private const string DefaultNotSupportedMessage = "Operation not supported";

        public static bool operator ==(BaseValue valueOne, BaseValue valueTwo)
        {
            return valueOne.Equals(valueTwo).Value;
        }

        public static bool operator !=(BaseValue valueOne, BaseValue valueTwo)
        {
            return !(valueOne == valueTwo);
        }

        public virtual IValue Parse(string value)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Add(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Substract(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(IntegerValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(DecimalValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(MoneyValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(DateValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(StringValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(BooleanValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Not()
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Negative()
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Positive()
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Divide(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual IValue Multiply(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThanOrEqualTo(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThanOrEqualTo(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue LessThan(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue GreaterThan(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Equals(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue Or(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }

        public virtual BooleanValue And(IValue other)
        {
            throw new InvalidOperationException(DefaultNotSupportedMessage);
        }
    }
}
