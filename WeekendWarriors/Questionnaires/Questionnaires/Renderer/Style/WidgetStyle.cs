using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Renderer.Style
{
    public class WidgetStyle
    {
        public WidgetStyle()
        {
            Width = 200;
            Font = "Arial";
            FontSize = 12;
            Color = 0;
        }

        public WidgetStyle(int width, String font, int fontSize, int color)
        {
            Width = width;
            Font = font;
            FontSize = fontSize;
            Color = color;
        }

        public int Width { get; set; }
        public String Font { get; set; }
        public int FontSize { get; set; }
        public int Color { get; set; }
    }
}
