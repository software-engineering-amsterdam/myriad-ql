using System.Collections.Generic;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
{
    public abstract class Container // TODO: should we make this abstract as we only want it to be inherited?
    {
        public string Name { get; set; }
        public List<Section> Sections { get; set; }
        public List<RunTime.Question> Questions { get; set; }

        public Container(string name)
        {
            Name = name;
            Sections = new List<Section>();
            Questions = new List<RunTime.Question>();
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
