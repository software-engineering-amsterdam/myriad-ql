using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
{
    public class DocumentModel
    {
        public List<Page> Pages { get; set; }

        public DocumentModel()
        {
            Pages = new List<Page>();
        }

        public void Clear()
        {
            Pages.Clear();
        }

        public void Draw(Panel target)
        {
            Pages.ForEach((page) => { page.Draw(target); });
        }

        public List<RunTime.Question> GetQuestions()
        {
            var questions = new List<RunTime.Question>();

            foreach (var page in Pages)
                GetQuestionsFromContainer(page, questions);

            return questions;
        }

        /* TODO: this is a horrible hack because we can't refactor everything at one. As it stands now, the
         * renderer needs direct access to the questions to hook up events */
        private void GetQuestionsFromContainer(Container container, List<RunTime.Question> questions)
        {
            questions.AddRange(container.Questions);
            foreach (var section in container.Sections)
                GetQuestionsFromContainer(section, questions);
        }
    }
}
