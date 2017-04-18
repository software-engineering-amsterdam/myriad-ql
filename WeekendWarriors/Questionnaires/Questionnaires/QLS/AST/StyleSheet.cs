using System.Collections.Generic;

namespace Questionnaires.QLS.AST
{
    public class StyleSheet : INode
    {
        public string Name { get; }
        public List<Page> Pages { get; }

        public StyleSheet(string name)
        {
            Pages = new List<Page>();
            Name = name;
        }

        public void AddPage(Page page)
        {
            Pages.Add(page);
        }

    }
}
