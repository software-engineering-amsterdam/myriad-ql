using System;

namespace Questionnaires.Renderer.Style
{
    public class WidgetStyle
    {
        public WidgetStyle()
        {
            Width = 200;
            Font = "Century Gothic";
            FontSize = 12;
            Color = "#000000";
        }

        public WidgetStyle(int width, String font, int fontSize, String color)
        {
            Width = width;
            Font = font;
            FontSize = fontSize;
            Color = color;
        }

        public int Width { get; set; }
        public string Font { get; set; }
        public int FontSize { get; set; }
        public string Color { get; set; }
    }
}
