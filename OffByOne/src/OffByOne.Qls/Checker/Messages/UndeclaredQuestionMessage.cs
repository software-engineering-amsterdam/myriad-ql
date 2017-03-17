namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;

    public class UndeclaredQuestionMessage : ErrorMessage
    {
        public UndeclaredQuestionMessage(string questionIdentifier, string description = "")
            : base($"Question {questionIdentifier} is not declared in the QL structure.", description)
        {
        }
    }
}
