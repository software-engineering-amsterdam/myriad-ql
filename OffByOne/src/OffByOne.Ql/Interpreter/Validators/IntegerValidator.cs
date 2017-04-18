namespace OffByOne.Ql.Interpreter.Validators
{
    using System.Text.RegularExpressions;

    using OffByOne.Ql.Common;
    using OffByOne.Ql.Interpreter.Validators.Base;

    public class IntegerValidator : IValidator<string>
    {
        public bool IsValid(string value)
        {
            var filter = new Regex(GlobalConstants.IntegerRegEx);
            return filter.IsMatch(value);
        }
    }
}
