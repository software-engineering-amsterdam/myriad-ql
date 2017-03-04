namespace OffByOne.Ql.Values
{
    using OffByOne.Ql.Values.Base;

    public class IntegerValue : BaseValue
    {
        public IntegerValue(string value)
            : this(int.Parse(value))
        {
        }

        public IntegerValue(int value)
        {
            this.Value = value;
        }

        public int Value { get; set; }
    }
}
