using System.Collections.Generic;

namespace Questionnaires.QLS.AST
{
    public class Page : INode
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
