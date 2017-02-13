namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class IntegerLiteral : Literal<int>
    {
        public IntegerLiteral(int value)
            : base(value)
        {
        }
    }
}
