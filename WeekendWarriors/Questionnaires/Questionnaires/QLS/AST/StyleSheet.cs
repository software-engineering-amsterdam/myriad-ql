using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    public class StyleSheet : INode
    {
        public string Name { get; }
        public List<Page> Pages { get; }

        public StyleSheet(string name)
        {
            Pages = new List<Page>();
            this.Name = name;
        }
        
        public void AddPage(Page page)
        {
            Pages.Add(page);
        }

    }
}
