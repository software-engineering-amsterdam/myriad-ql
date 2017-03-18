﻿using System.Windows.Controls;

namespace Questionnaires.Renderer.Containers
{
    public class Page : SimpleContainer
    {
        public Page(string name) : base(name)
        {

        }

        protected override TextBlock GetHeader()
        {
            var header = new TextBlock();
            header.Text = "Page " + Name;
            header.FontSize = 40;
            return header;
        }
    }
}
