using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Types;
using System.Windows.Controls;
using System.Windows.Markup;
using System.Windows;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class CheckBoxWidget : QuestionWidget
    {
        private TextBlock QuestionLabelWidget = new TextBlock();
        private CheckBox QuestionInputWidget = new CheckBox();

        public CheckBoxWidget()
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

        public void SetQuestionValue(BooleanType value)
        {
            QuestionInputWidget.IsChecked = value.GetValue();
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
            QuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(this, new BooleanType(QuestionInputWidget.IsChecked.Value));
            QuestionInputWidget.Unchecked += (sender, args) => inputChanged.Invoke(this, new BooleanType(QuestionInputWidget.IsChecked.Value));
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
