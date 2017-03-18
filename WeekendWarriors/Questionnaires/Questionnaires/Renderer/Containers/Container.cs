using System.Collections.Generic;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
{
    public abstract class Container 
    {
        protected string Name { get; set; }
        private List<Section> Sections;
        private List<RunTime.Question> Questions;

        public Container(string name)
        {
            Name = name;
            Sections = new List<Section>();
            Questions = new List<RunTime.Question>();
        }

        public void AddSection(Section section)
        {
            Sections.Add(section);
        }

        public void AddQuestion(RunTime.Question question)
        {
            Questions.Add(question);
        }

        public void Draw(Panel target)
        {
            var container = GetContainer();

            foreach (var question in Questions)
            {
                container.Children.Add(question.Widget);
            }

            foreach (var section in Sections)
            {
                section.Draw(container);
            }

            target.Children.Add(container);
        }

        protected abstract Panel GetContainer();
    }
}
