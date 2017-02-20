namespace OffByOne.LanguageCore.Checker
{
    public class CheckerMessage
    {
        public CheckerMessage(string message, LogLevel level)
            : this(message, null, level)
        {
        }

        public CheckerMessage(string message, string description, LogLevel level)
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
