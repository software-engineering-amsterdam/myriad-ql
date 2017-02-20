namespace OffByOne.LanguageCore.Ast.Literals
{
    using System;
    using System.Globalization;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DateLiteral : Literal
    {
        public const string FORMAT = "dd-MM-yyyy";

        public DateLiteral(DateTime value)
        {
            this.Value = value;
        }

        public DateLiteral(string dateString)
            : this(DateTime.ParseExact(
                dateString.Trim('\''),
                DateLiteral.FORMAT,
                CultureInfo.InvariantCulture))
        {
        }

        public DateTime Value { get; private set; }
    }
}
