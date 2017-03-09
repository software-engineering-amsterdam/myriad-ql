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
    class RadioWidget : QuestionWidget
    {
        private TextBlock QuestionLabelWidget = new TextBlock();
        private RadioButton FirstQuestionInputWidget = new RadioButton();
        private RadioButton SecondQuestionInputWidget = new RadioButton();

        public RadioWidget()
            : base()
        {
            Orientation = Orientation.Horizontal;

            FirstQuestionInputWidget.Content = "Yes";
            SecondQuestionInputWidget.Content = "No";

            Children.Add(QuestionLabelWidget);
            Children.Add(FirstQuestionInputWidget);
            Children.Add(SecondQuestionInputWidget);

            SecondQuestionInputWidget.IsChecked = true;
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
            if (value.GetValue())
            {
                FirstQuestionInputWidget.IsChecked = true;
            }
            else
            {
                SecondQuestionInputWidget.IsChecked = true;
            }
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
            FirstQuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(this, new BooleanType(true));
            SecondQuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(this, new BooleanType(false));
        }
        
        public override void SetStyle(WidgetStyle style)
        {
            QuestionLabelWidget.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString(style.Color));
            FirstQuestionInputWidget.Width = style.Width;
            SecondQuestionInputWidget.Width = style.Width;
            QuestionLabelWidget.FontFamily = new FontFamily(style.Font);
            QuestionLabelWidget.FontSize = style.FontSize;
        }
    }
}
