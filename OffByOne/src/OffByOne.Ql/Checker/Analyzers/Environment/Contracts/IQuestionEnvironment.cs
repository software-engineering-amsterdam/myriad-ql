namespace OffByOne.Ql.Checker.Analyzers.Environment.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public interface IQuestionEnvironment : IEnvironment
    {
        void AddQuestionIdentifier(string name);

        void AddQuestionLabel(StringValue label);

        bool IsIdentifierDuplicate(string name);

        bool IsLabelDuplicate(StringValue label);
    }
}