namespace OffByOne.LanguageCore.Checker.Messages.Base
{
    public abstract class CheckerMessage
    {
        protected CheckerMessage(string message, LogLevel level, string description = "")
        {
            this.Message = message;
            this.Description = description;
            this.Level = level;
        }

        public string Message { get; }

        public string Description { get; }

        public LogLevel Level { get; }

        public override string ToString()
        {
            return $"{this.Level} : {this.Message} - {this.Description}";
        }
    }
}
