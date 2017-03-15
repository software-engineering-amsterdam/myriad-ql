namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Windows;
    using System.Windows.Controls;
    using System.Windows.Media;

    public class WidgetStyle
    {
        public FontStyle FontStyle { get; set; }

        public FontFamily FontFamily { get; set; }

        public double Width { get; set; }

        public double Height { get; set; }

        public double FontSize { get; set; }

        public Color Color { get; set; }

        public void Apply(Control control)
        {
            if (this.FontStyle == null)
            {
                control.FontStyle = this.FontStyle;
            }

            if (this.FontFamily != null)
            {
                control.FontFamily = this.FontFamily;
            }

            if (this.Width > 0)
            {
                control.MinWidth = this.Width;
            }

            if (this.Height > 0)
            {
                control.MinHeight = this.Height;
            }

            if (this.FontSize > 0)
            {
                control.FontSize = this.FontSize;
            }

            if (this.Color != null)
            {
                control.Foreground = new SolidColorBrush(this.Color);
            }
        }
    }
}
