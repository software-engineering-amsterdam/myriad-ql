using System.Windows.Controls;

namespace Questionnaires.RunTime.DocumentModel
{
    public class Section : SimpleContainer
    {
        public Section(string name) : base(name)
        {
        }

        protected override TextBlock GetHeader()
        {
            var header = new TextBlock();
            header.Text = "Section " + Name;
            header.FontSize = 20;
            return header;
        }
    }
}
