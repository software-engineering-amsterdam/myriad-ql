namespace OffByOne.LanguageCore.Ast.Style.Properties.Base
{
    public abstract class Property : AstNode
    {
        protected Property(string value)
        {
            this.Value = value;
        }

        public string Value { get; private set; }
    }
}
