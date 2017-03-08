using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class TextBoxWidget : QuestionWidget
    {
        private TextBlock QuestionLabelWidget = new TextBlock();
        private TextBox QuestionInputWidget = new TextBox();

        public TextBoxWidget()
            : base()
        {
            Orientation = Orientation.Horizontal;
            Children.Add(QuestionLabelWidget);
            Children.Add(QuestionInputWidget);
        }

        public override void SetLabel(string text)
        {
            QuestionLabelWidget.Text = text;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(StringType value)
        {
            QuestionInputWidget.Text = value.GetValue();
        }

        public override void SetVisibility(bool visible)
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

        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            QuestionInputWidget.TextChanged += (sender, args) => inputChanged.Invoke(this, new StringType(QuestionInputWidget.Text));
        }

        public override void SetStyle(WidgetStyle style)
        {
            QuestionLabelWidget.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString(style.Color.ToString()));
            QuestionInputWidget.Width = style.Width;
            QuestionLabelWidget.FontFamily = new FontFamily(style.Font);
            QuestionLabelWidget.FontSize = style.FontSize;
        }
    }
}
