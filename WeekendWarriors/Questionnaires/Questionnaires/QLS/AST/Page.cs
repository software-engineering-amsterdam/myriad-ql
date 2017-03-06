using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    class Page : INode
    {
        public string Name { get; }
        public List<Section> Sections { get; }
        public List<DefaultStyle> DefaultStyles { get; }

        public Page(string name)
        {
            Sections = new List<Section>();
            DefaultStyles = new List<DefaultStyle>();
            this.Name = name;
        }

        public void AddSection(Section section)
        {
            Sections.Add(section);
        }

        public void AddDefaultStyle(DefaultStyle style)
        {
            DefaultStyles.Add(style);
        }
    }
}
