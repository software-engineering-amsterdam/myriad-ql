namespace OffByOne.Ql.Values
{
    using System;
    using System.Globalization;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class DateValue : BaseValue
    {
        public DateValue(string value)
            : this(DateTime.Parse(value, CultureInfo.InvariantCulture))
        {
        }

        public DateValue(DateTime value)
        {
            this.Value = value;
        }

        public DateTime Value { get; set; }

        public override IValue Parse(string value)
        {
            return new DateValue(value);
        }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override IValue Add(StringValue other)
        {
            return new StringValue(other.Value + this.Value);
        }

        public override BooleanValue Equals(IValue other)
        {
            return other.Equals(this);
        }

        public override BooleanValue Equals(DateValue other)
        {
            return new BooleanValue(this.Value == other.Value);
        }

        public override BooleanValue GreaterThan(IValue other)
        {
            return other.GreaterThan(this);
        }

        public override BooleanValue GreaterThan(DateValue other)
        {
            return new BooleanValue(this.Value > other.Value);
        }

        public override BooleanValue LessThan(IValue other)
        {
            return other.LessThan(this);
        }

        public override BooleanValue LessThan(DateValue other)
        {
            return new BooleanValue(this.Value < other.Value);
        }

        public override BooleanValue GreaterThanOrEqualTo(IValue other)
        {
            return other.GreaterThanOrEqualTo(this);
        }

        public override BooleanValue GreaterThanOrEqualTo(DateValue other)
        {
            return new BooleanValue(this.Value >= other.Value);
        }

        public override BooleanValue LessThanOrEqualTo(IValue other)
        {
            return other.LessThanOrEqualTo(this);
        }

        public override BooleanValue LessThanOrEqualTo(DateValue other)
        {
            return new BooleanValue(this.Value <= other.Value);
        }

        public override string ToString()
        {
            return this.Value.ToString(CultureInfo.InvariantCulture);
        }
    }
}
