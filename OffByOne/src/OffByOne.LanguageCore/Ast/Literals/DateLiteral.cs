namespace OffByOne.LanguageCore.Ast.Literals
{
    using System;
    using System.Globalization;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DateLiteral : Literal<DateTime>
    {
        public DateLiteral(DateTime value)
            : base(value)
        {
        }

        public DateLiteral(string dateString)
            : this(DateTime.Parse(dateString, CultureInfo.InvariantCulture))
        {
        }
    }
}
