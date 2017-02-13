namespace OffByOne.LanguageCore.Ast.Literals.Base
{
    using OffByOne.LanguageCore.Ast.Expressions;

    public abstract class Literal<T> : Expression
    {
        protected Literal(T value)
        {
            this.Value = value;
        }

        public T Value { get; private set; }
    }
}
