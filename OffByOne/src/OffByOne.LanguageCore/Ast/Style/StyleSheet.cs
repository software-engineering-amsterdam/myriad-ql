namespace OffByOne.LanguageCore.Ast.Style
{
    using System.Collections.Generic;

    public class StyleSheet : AstNode
    {
        public StyleSheet(
            string id,
            ICollection<Page> pages)
        {
            this.Id = id;
            this.Pages = pages;
        }

        public string Id { get; private set; }

        public ICollection<Page> Pages { get; private set; }
    }
}
