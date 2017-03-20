namespace OffByOne.Ql.Values
{
    using System.Globalization;

    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class BooleanValue : BaseValue
    {
        public BooleanValue(string value)
            : this(bool.Parse(value))
        {
        }

        public BooleanValue(bool value = false)
        {
            this.Value = value;
        }

        public bool Value { get; }

        public override IValue Parse(string value)
        {
            return new BooleanValue(value);
        }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override IValue Add(StringValue other)
        {
            return new StringValue(other.Value + this.Value.ToString(CultureInfo.InvariantCulture));
        }

        public override BooleanValue Equals(IValue other)
        {
            return other.Equals(this);
        }

        public override BooleanValue Equals(BooleanValue other)
        {
            return new BooleanValue(other.Value == this.Value);
        }

        public override BooleanValue And(IValue other)
        {
            return other.And(this);
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

        public override int GetHashCode()
        {
            return this.Value.GetHashCode();
        }

        public override bool Equals(object obj)
        {
            return obj.Is<BooleanValue>() && obj.As<BooleanValue>().Value == this.Value;
        }
    }
}
