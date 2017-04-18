namespace OffByOne.Ql.Checker.Messages.Base
{
    public abstract class ErrorMessage : CheckerMessage
    {
        protected ErrorMessage(string message, string description = "")
            : base(message, description)
        {
        }

        public override string ToString()
        {
            return $"ERROR: {this.Message} - {this.Description}";
        }
    }
}
