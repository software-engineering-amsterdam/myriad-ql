namespace OffByOne.Ql.Graphics.Widgets
{
    using System;
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Graphics.Widgets.Base;
    using OffByOne.Ql.Values.Contracts;

    public class TextBoxWidget : UiComponent
    {
        private readonly TextBox control;
        private readonly Window window;
        private WidgetStyle style;

        public TextBoxWidget()
        {
            this.control = new TextBox();
            this.window = new Window();
            var stackPanel = new StackPanel { Orientation = Orientation.Vertical };
            stackPanel.Children.Add(this.control);
            this.window.Content = stackPanel;
        }

        public override IValue Value { get; set; }

        public override Control Control => this.control;
    }
}
