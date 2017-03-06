namespace OffByOne.Ql.Checker.Messages.Base
{
    public class ErrorMessage : CheckerMessage
    {
        public ErrorMessage(string message, string description = "")
            : base(message, description)
        {
        }

        public override string ToString()
        {
            return $"{this.Level.ToUpper()}: {this.Message} - {this.Description}";
        }
    }
}
