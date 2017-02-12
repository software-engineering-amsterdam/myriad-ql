namespace OffByOne.LanguageCore.Ast.Style
{
    using System.Collections.Generic;

    public class Page : AstNode
    {
        public Page(string name, ICollection<Section> sections)
        {
            this.Name = name;
            this.Sections = sections;
        }

        public string Name { get; private set; }

        public ICollection<Section> Sections { get; private set; }
    }
}
