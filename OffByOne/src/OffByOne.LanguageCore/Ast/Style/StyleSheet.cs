namespace OffByOne.LanguageCore.Ast.Style
{
    using System.Collections.Generic;

    public class StyleSheet : AstNode
    {
        public StyleSheet(
            string id,
            ICollection<AstNode> pages)
        {
            this.Id = id;
            this.Nodes = pages;
        }

        public string Id { get; private set; }

        public ICollection<AstNode> Nodes { get; private set; }
    }
}
