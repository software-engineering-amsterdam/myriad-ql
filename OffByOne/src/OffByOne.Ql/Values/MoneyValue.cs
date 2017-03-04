namespace OffByOne.Ql.Values
{
    using OffByOne.Ql.Values.Base;

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
    }
}
