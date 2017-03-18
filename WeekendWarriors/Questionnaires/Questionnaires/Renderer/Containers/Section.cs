using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
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
