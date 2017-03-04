namespace OffByOne.Ql.Values
{
    using System.Globalization;

    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class MoneyValue : BaseValue
    {
        public MoneyValue(string value)
            : this(decimal.Parse(value))
        {
        }

        public MoneyValue(decimal value)
        {
            this.Value = value;
        }

        public decimal Value { get; set; }

        public override IValue Parse(string value)
        {
            return new MoneyValue(value);
        }

        public override IValue Add(IValue other)
        {
            return other.Add(this);
        }

        public override IValue Add(DecimalValue other)
        {
            return new MoneyValue(this.Value + (decimal)other.Value);
        }

        public override IValue Add(MoneyValue other)
        {
            return new MoneyValue(this.Value + other.Value);
        }

        public override IValue Add(IntegerValue other)
        {
            return new MoneyValue(this.Value + other.Value);
        }

        public override IValue Add(StringValue other)
        {
            return new StringValue(other.Value + this.Value);
        }

        public override IValue Substract(IValue other)
        {
            return other.Substract(this);
        }

        public override IValue Substract(DecimalValue other)
        {
            return new MoneyValue(this.Value - (decimal)other.Value);
        }

        public override IValue Substract(MoneyValue other)
        {
            return new MoneyValue(this.Value - other.Value);
        }

        public override IValue Substract(IntegerValue other)
        {
            return new MoneyValue(this.Value - other.Value);
        }

        public override IValue Divide(IValue other)
        {
            return other.Divide(this);
        }

        public override IValue Divide(DecimalValue other)
        {
            return new MoneyValue((decimal)other.Value / this.Value);
        }

        public override IValue Divide(MoneyValue other)
        {
            return new MoneyValue(other.Value / this.Value);
        }

        public override IValue Divide(IntegerValue other)
        {
            return new MoneyValue(other.Value / this.Value);
        }

        public override IValue Multiply(IValue other)
        {
            return other.Multiply(this);
        }

        public override IValue Multiply(DecimalValue other)
        {
            return new MoneyValue(this.Value * (decimal)other.Value);
        }

        public override IValue Multiply(MoneyValue other)
        {
            return new MoneyValue(other.Value * this.Value);
        }

        public override IValue Multiply(IntegerValue other)
        {
            return new MoneyValue(this.Value * other.Value);
        }

        public override BooleanValue LessThan(IValue other)
        {
            return other.LessThan(this);
        }

        public override BooleanValue LessThan(DecimalValue other)
        {
            return new BooleanValue(this.Value < (decimal)other.Value);
        }

        public override BooleanValue LessThan(MoneyValue other)
        {
            return new BooleanValue(this.Value < other.Value);
        }

        public override BooleanValue LessThan(IntegerValue other)
        {
            return new BooleanValue(this.Value < other.Value);
        }

        public override BooleanValue GreaterThan(IValue other)
        {
            return other.GreaterThan(this);
        }

        public override BooleanValue GreaterThan(DecimalValue other)
        {
            return new BooleanValue(this.Value > (decimal)other.Value);
        }

        public override BooleanValue GreaterThan(MoneyValue other)
        {
            return new BooleanValue(this.Value > other.Value);
        }

        public override BooleanValue GreaterThan(IntegerValue other)
        {
            return new BooleanValue(this.Value > other.Value);
        }

        public override BooleanValue GreaterThanOrEqualTo(IValue other)
        {
            return other.GreaterThanOrEqualTo(this);
        }

        public override BooleanValue GreaterThanOrEqualTo(MoneyValue other)
        {
            return new BooleanValue(this.Value >= other.Value);
        }

        public override BooleanValue GreaterThanOrEqualTo(IntegerValue other)
        {
            return new BooleanValue(this.Value >= other.Value);
        }

        public override BooleanValue GreaterThanOrEqualTo(DecimalValue other)
        {
            return new BooleanValue(this.Value >= (decimal)other.Value);
        }

        public override BooleanValue LessThanOrEqualTo(IValue other)
        {
            return other.LessThanOrEqualTo(this);
        }

        public override BooleanValue LessThanOrEqualTo(DecimalValue other)
        {
            return new BooleanValue(this.Value <= (decimal)other.Value);
        }

        public override BooleanValue LessThanOrEqualTo(MoneyValue other)
        {
            return new BooleanValue(this.Value <= other.Value);
        }

        public override BooleanValue LessThanOrEqualTo(IntegerValue other)
        {
            return new BooleanValue(this.Value <= other.Value);
        }

        public override IValue Negative()
        {
            return new MoneyValue(-this.Value);
        }

        public override IValue Positive()
        {
            return new MoneyValue(+this.Value);
        }

        public override string ToString()
        {
            return this.Value.ToString(CultureInfo.InvariantCulture);
        }
    }
}
