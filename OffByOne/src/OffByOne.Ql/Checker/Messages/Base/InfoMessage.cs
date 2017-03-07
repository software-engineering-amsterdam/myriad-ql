namespace OffByOne.Ql.Checker.Messages.Base
{
    public abstract class InfoMessage : CheckerMessage
    {
        protected InfoMessage(string message, string description = "")
            : base(message, description)
        {
        }

        public override string ToString()
        {
            return "INFO: " + base.ToString();
        }
    }
}
