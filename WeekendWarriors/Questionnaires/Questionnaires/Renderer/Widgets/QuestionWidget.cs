using System.Windows.Controls;
using Questionnaires.Types;
using Questionnaires.Renderer.Style;
using System.Windows;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    public abstract class QuestionWidget : StackPanel
    {
        protected TextBlock Label = new TextBlock();
        protected FrameworkElement Control;

        public QuestionWidget(FrameworkElement control)
        {
            Control = control;
            Orientation = Orientation.Horizontal;
            Children.Add(Label);
            Children.Add(Control);
        }

        public void SetLabel(string text)
        {
            Label.Text = text;
        }

        public void SetVisibility(bool visible)
        {
            if (visible)
            {
                Visibility = System.Windows.Visibility.Visible;
            }
            else
            {
                Visibility = System.Windows.Visibility.Hidden;
            }
        }

        public void SetStyle(WidgetStyle style)
        {
            Label.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString(style.Color));
            Label.FontFamily = new FontFamily(style.Font);
            Label.FontSize = style.FontSize;
            Control.Width = style.Width;
        }

        public delegate void InputChangedEventHandler(object sender, IType newValue);
        public event InputChangedEventHandler InputChanged;
        protected void OnInputChanged(IType newValue)
        {
            if (InputChanged != null)
                InputChanged(this, newValue);
        }
        public abstract void SetQuestionValue(IType value);
    }
}
