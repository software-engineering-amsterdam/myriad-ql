namespace OffByOne.Ql.Values
{
    using System;
    using System.Globalization;

    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class StringValue : BaseValue
    {
        public StringValue(string value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "String value must not be null");
            }

            this.Value = value.Trim('"');
        }

        public string Value { get; }

        public override IValue Parse(string value)
        {
            return new StringValue(value);
        }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override IValue Add(IntegerValue other)
        {
            return new StringValue(this.Value + other.Value);
        }

        public override IValue Add(DecimalValue other)
        {
            return new StringValue(this.Value + other.Value);
        }

        public override IValue Add(MoneyValue other)
        {
            return new StringValue(this.Value + other.Value);
        }

        public override IValue Add(DateValue other)
        {
            return new StringValue(this.Value + other.Value);
        }

        public override IValue Add(StringValue other)
        {
            return new StringValue(other.Value + this.Value);
        }

        public override IValue Add(BooleanValue other)
        {
            return new StringValue(this.Value + other.Value);
        }

        public override BooleanValue Equals(IValue other)
        {
            return other.Equals(this);
        }

        public override BooleanValue Equals(StringValue other)
        {
            return new BooleanValue(this.Value == other.Value);
        }

        public override string ToString()
        {
            return this.Value.ToString(CultureInfo.InvariantCulture);
        }

        public override int GetHashCode()
        {
            return this.Value.GetHashCode();
        }

        public override bool Equals(object obj)
        {
            return obj.Is<StringValue>() && obj.As<StringValue>().Value == this.Value;
        }
    }
}
