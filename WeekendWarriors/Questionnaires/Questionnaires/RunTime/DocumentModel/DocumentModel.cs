using System.Collections.Generic;
using System.Windows.Controls;

namespace Questionnaires.RunTime.DocumentModel
{
    public class DocumentModel
    {
        private List<Page> Pages;

        public DocumentModel()
        {
            Pages = new List<Page>();
        }

        public void Clear()
        {
            Pages.Clear();
        }

        public void AddPage(Page page)
        {
            Pages.Add(page);
        }

        public void Draw(Panel target)
        {
            Pages.ForEach((page) => { page.Draw(target); });
        }
    }
}
