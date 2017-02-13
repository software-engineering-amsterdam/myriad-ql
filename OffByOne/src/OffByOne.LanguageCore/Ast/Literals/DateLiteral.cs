namespace OffByOne.LanguageCore.Ast.Literals
{
    using System;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DateLiteral : Literal
    {
        public DateLiteral(DateTime value)
        {
            this.Value = value;
        }

        // TODO: Is this the right place for parsing the date?
        // TODO: Or should we do it completely different anyway?
        // TODO: Parse the date
        public DateLiteral(string dateString)
        {
            int day = int.Parse(dateString.Substring(1, 2));
            int month = int.Parse(dateString.Substring(4, 2));
            int year = int.Parse(dateString.Substring(7, 4));
            this.Value = new DateTime(year, month, day);
        }

        public DateTime Value { get; private set; }
    }
}
