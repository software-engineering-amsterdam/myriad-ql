using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Xceed.Wpf.Toolkit;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class DecimalPickerWidget : QuestionWidget
    {
        private TextBlock QuestionLabelWidget = new TextBlock();
        private DecimalUpDown QuestionInputWidget = new DecimalUpDown();

        public DecimalPickerWidget()
            : base()
        {
            Orientation = Orientation.Horizontal;
            Children.Add(QuestionLabelWidget);
            Children.Add(QuestionInputWidget);
            
            QuestionInputWidget.AllowTextInput = false;
        }

        public override void SetLabel(string text)
        {
            QuestionLabelWidget.Text = text;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            QuestionInputWidget.Text = value.GetValue().ToString();
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
            QuestionInputWidget.ValueChanged += (sender, args) => inputChanged.Invoke(this, new MoneyType(QuestionInputWidget.Value.Value));
        }

        public override void SetStyle(WidgetStyle style)
        {
            QuestionLabelWidget.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString(style.Color));
            QuestionInputWidget.Width = style.Width;
            QuestionLabelWidget.FontFamily = new FontFamily(style.Font);
            QuestionLabelWidget.FontSize = style.FontSize;
        }
    }
}
