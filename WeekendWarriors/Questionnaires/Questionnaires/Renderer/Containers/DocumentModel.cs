using System.Collections.Generic;
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
    }
}
