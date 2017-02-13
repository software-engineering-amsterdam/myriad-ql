namespace OffByOne.LanguageCore.Ast.Literals
{
    using System;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DateLiteral : Literal<DateTime>
    {
        public DateLiteral(DateTime value)
            : base(value)
        {
        }

        // TODO: DONT DO THIS HERE OR IN THIS WAY...
        public static DateTime Parse(string dateString)
        {
            int day = int.Parse(dateString.Substring(1, 2));
            int month = int.Parse(dateString.Substring(4, 2));
            int year = int.Parse(dateString.Substring(7, 4));
            return new DateTime(year, month, day);
        }
    }
}
