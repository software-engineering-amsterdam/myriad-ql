using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class NumberQuestionWidget : QuestionWidget
    {
        private String QuestionName;
        private TextBlock QuestionLabelWidget = new TextBlock();
        private TextBox QuestionInputWidget = new TextBox();

        public NumberQuestionWidget(string name)
            : base()
        {
            QuestionName = name;
            Orientation = Orientation.Horizontal;
            Children.Add(QuestionLabelWidget);
            Children.Add(QuestionInputWidget);
        }

        public override void SetLabel(string text)
        {
            QuestionLabelWidget.Text = text;
        }

        public override void SetQuestionValue(IValue value)
        {
            QuestionInputWidget.Text = value.AsInt().ToString();
        }

        public override void SetVisibility(Question.Visibility visibility)
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
        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            QuestionInputWidget.TextChanged += (sender, args) => inputChanged.Invoke(QuestionName, new IntValue(int.Parse(QuestionInputWidget.Text)));
        }
    }
}
