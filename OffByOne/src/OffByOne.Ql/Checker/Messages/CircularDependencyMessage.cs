namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;

    public class CircularDependencyMessage : ErrorMessage
    {
        public CircularDependencyMessage(string questionId, string description = "")
            : base($"Circular dependency for question {questionId}!", description)
        {
        }
    }
}
