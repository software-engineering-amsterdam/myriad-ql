using System.Collections.Generic;
using System.Windows.Controls;

namespace Questionnaires.RunTime.DocumentModel
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

            AddQuestionsToContainer(container);
            AddSectionsToContainer(container);

            target.Children.Add(container);
        }

        private void AddSectionsToContainer(Panel container)
        {
            foreach (var section in Sections)
            {
                section.Draw(container);
            }
        }

        private void AddQuestionsToContainer(Panel container)
        {
            foreach (var question in Questions)
            {
                question.Draw(container);
            }
        }

        protected abstract Panel GetContainer();
    }
}
