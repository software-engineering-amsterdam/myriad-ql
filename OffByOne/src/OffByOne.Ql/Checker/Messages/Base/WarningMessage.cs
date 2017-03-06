namespace OffByOne.Ql.Checker.Messages.Base
{
    public class WarningMessage : CheckerMessage
    {
        public WarningMessage(string message, string description = "")
            : base("Warning", message, description)
        {
        }
    }
}
