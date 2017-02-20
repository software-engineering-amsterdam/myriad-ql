namespace OffByOne.LanguageCore.Ast.Literals
{
    using System;
    using System.Globalization;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DateLiteral : Literal
    {
        public DateLiteral(DateTime value)
        {
            this.Value = value;
        }

        public DateLiteral(string dateString)
            : this(DateTime.Parse(dateString.Trim('\''), CultureInfo.InvariantCulture))
        {
        }

        public DateTime Value { get; private set; }
    }
}
