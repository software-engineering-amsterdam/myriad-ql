namespace OffByOne.Ql.Values
{
    using System.Globalization;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class StringValue : BaseValue
    {
        public StringValue(string value)
        {
            this.Value = value;
        }

        public string Value { get; set; }

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
    }
}
