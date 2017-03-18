namespace OffByOne.Ql.Checker.Analyzers.Environment.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface IQuestionEnvironment : IEnvironment
    {
        void AddQuestionIdentifier(string name);

        void AddQuestionLabel(string label);

        bool IsIdentifierDuplicate(string name);

        bool IsLableDuplicate(string label);
    }
}