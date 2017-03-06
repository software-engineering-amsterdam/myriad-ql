using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    class Section : INode
    {
        public string Name {  get; }
        public List<Section> Sections { get; }
        public List<Question> Questions { get; }
        public List<DefaultStyle> Styles { get; }
        public Section(string name)
        {
            Sections = new List<Section>();
            Questions = new List<Question>();
            Styles = new List<DefaultStyle>();
            this.Name = name;
        }

        public void AddChild(Section section)
        {
            Sections.Add(section);
        }

        public void AddChild(Question question)
        {
            Questions.Add(question);
        }

        public void AddChild(DefaultStyle style)
        {
            Styles.Add(style);
        }

    }
}
