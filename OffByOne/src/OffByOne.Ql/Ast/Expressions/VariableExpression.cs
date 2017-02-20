namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;

    public class VariableExpression : Expression
    {
        public VariableExpression(string identifier)
        {
            this.Identifier = identifier;
        }

        public string Identifier { get; private set; }
    }
}
