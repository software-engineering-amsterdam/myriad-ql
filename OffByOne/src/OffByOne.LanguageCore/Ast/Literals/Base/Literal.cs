namespace OffByOne.LanguageCore.Ast.Literals.Base
{
    public abstract class Literal : AstNode
    {
        protected Literal(T value)
        {
            this.Value = value;
        }

        public T Value { get; private set; }
    }
}
