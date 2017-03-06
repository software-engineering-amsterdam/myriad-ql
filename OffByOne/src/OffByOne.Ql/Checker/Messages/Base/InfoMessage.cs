namespace OffByOne.Ql.Checker.Messages.Base
{
    public class InfoMessage : CheckerMessage
    {
        public InfoMessage(string message, string description = "")
            : base("Info", message, description)
        {
        }
    }
}
