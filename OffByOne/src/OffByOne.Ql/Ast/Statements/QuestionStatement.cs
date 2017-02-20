namespace OffByOne.Ql.Ast.Statements
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class QuestionStatement : Statement
    {
        public QuestionStatement(
            string identifier,
            ValueType type,
            string question)
        {
            this.Identifier = identifier;
            this.Type = type;
            this.Question = new StringLiteral(question);
        }

        public string Identifier { get; private set; }

        public ValueType Type { get; private set; }

        public StringLiteral Question { get; private set; }
    }
}
