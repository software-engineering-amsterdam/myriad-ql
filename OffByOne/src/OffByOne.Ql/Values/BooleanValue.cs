namespace OffByOne.Ql.Values
{
    using System.Globalization;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class BooleanValue : BaseValue
    {
        public BooleanValue(string value)
            : this(bool.Parse(value))
        {
        }

        public BooleanValue(bool value)
        {
            this.Value = value;
        }

        public bool Value { get; set; }

        public override IValue Parse(string value)
        {
            return new BooleanValue(value);
        }

        public override BooleanValue Equals(IValue other)
        {
            return other.Equals(this);
        }

        public override BooleanValue Equals(BooleanValue other)
        {
            return new BooleanValue(other.Value == this.Value);
        }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override BooleanValue And(BooleanValue other)
        {
            return new BooleanValue(this.Value && other.Value);
        }

        public override BooleanValue Or(IValue other)
        {
            return other.Or(this);
        }

        public override BooleanValue Or(BooleanValue other)
        {
            return new BooleanValue(this.Value || other.Value);
        }

        public override BooleanValue Not()
        {
            return new BooleanValue(!this.Value);
        }

        public override string ToString()
        {
            return this.Value.ToString(CultureInfo.InvariantCulture);
        }
    }
}
