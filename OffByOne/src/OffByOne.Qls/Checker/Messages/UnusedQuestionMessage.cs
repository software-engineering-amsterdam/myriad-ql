namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;

    public class UnusedQuestionMessage : ErrorMessage
    {
        public UnusedQuestionMessage(string questionIdentifier, string description = "")
            : base($"Question {questionIdentifier} has no QLS style.", description)
        {
        }
    }
}
