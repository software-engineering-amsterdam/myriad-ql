namespace OffByOne.LanguageCore.Ast.Style
{
    public class StyleSheet : AstNode
    {
        public StyleSheet(string id)
        {
            this.Id = id;
        }

        public string Id { get; private set; }
    }
}
