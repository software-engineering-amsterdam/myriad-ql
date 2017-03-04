namespace OffByOne.Ql.Values
{
    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class DecimalValue : BaseValue
    {
        public DecimalValue(string value)
            : this(double.Parse(value))
        {
        }

        public DecimalValue(double value)
        {
            this.Value = value;
        }

        public double Value { get; set; }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override IValue Add(DecimalValue other)
        {
            return new DecimalValue(this.Value + other.Value);
        }

        public override IValue Add(MoneyValue other)
        {
            return new MoneyValue((decimal)this.Value + other.Value);
        }

        public override IValue Add(IntegerValue other)
        {
            return new DecimalValue(this.Value + other.Value);
        }

        public override IValue Substract(IValue other)
        {
            return other.Substract(this);
        }

        public override IValue Substract(DecimalValue other)
        {
            return new DecimalValue(this.Value - other.Value);
        }

        public override IValue Substract(MoneyValue other)
        {
            return new MoneyValue((decimal)this.Value - other.Value);
        }

        public override IValue Substract(IntegerValue other)
        {
            return new DecimalValue(this.Value - other.Value);
        }
    }
}
