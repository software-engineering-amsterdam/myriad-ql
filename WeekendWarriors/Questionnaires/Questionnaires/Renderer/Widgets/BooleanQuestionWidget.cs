using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Question;
using Questionnaires.Value;
using System.Windows.Controls;
using System.Windows.Markup;
using System.Windows;

namespace Questionnaires.Renderer.Widgets
{
    class BooleanQuestionWidget : StackPanel, IQuestionWidget
    {
        private TextBlock QuestionLabelWidget = new TextBlock();
        private CheckBox QuestionInputWidget = new CheckBox();

        public BooleanQuestionWidget()
            : base()
        {
            Orientation = Orientation.Horizontal;
            Children.Add(QuestionLabelWidget);
            Children.Add(QuestionInputWidget);
        }

        public void SetLabel(string text)
        {
            QuestionLabelWidget.Text = text;
        }

        public void SetQuestionValue(IValue value)
        {
            QuestionInputWidget.IsChecked = value.AsBool();
        }

        public void SetVisibility(Question.Visibility visibility)
        {
            if (visibility == Question.Visibility.Visible)
            {
                Visibility = System.Windows.Visibility.Visible;
            }
            else
            {
                Visibility = System.Windows.Visibility.Hidden;
            }
        }
    }
}
