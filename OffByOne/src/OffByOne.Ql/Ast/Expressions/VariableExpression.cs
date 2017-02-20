namespace OffByOne.Ql.Ast.Expressions
{
    public class VariableExpression : Expression
    {
        public VariableExpression(string identifier)
        {
            this.Identifier = identifier;
        }

        public string Identifier { get; private set; }
    }
}
