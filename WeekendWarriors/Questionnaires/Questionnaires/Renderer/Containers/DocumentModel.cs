using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
    }
}
