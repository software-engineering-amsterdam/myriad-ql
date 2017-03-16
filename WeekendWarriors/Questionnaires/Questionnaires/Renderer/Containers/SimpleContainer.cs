using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
{
    /* Abstact class for very simple container types. The container is nothing more than a 
     * stack panel with a header. Scope is shown solely by indentation */
    public abstract class SimpleContainer : Container
    {
        public SimpleContainer(string name) : base(name)
        {
        }

        protected override Panel GetContainer()
        {
            var container = new StackPanel();
            container.Orientation = Orientation.Vertical;
            container.Margin = new System.Windows.Thickness(50, 5, 0, 0);
            container.Children.Add(GetHeader());
            return container;
        }

        protected abstract TextBlock GetHeader();
    }
}
