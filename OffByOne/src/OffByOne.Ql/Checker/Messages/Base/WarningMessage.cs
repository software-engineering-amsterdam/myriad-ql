namespace OffByOne.Ql.Checker.Messages.Base
{
    public abstract class WarningMessage : CheckerMessage
    {
        protected WarningMessage(string message, string description = "")
            : base(message, description)
        {
        }

        public override string ToString()
        {
            return "WARNING: " + base.ToString();
        }
    }
}
