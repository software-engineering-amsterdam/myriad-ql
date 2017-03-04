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
    class BooleanQuestionWidget : QuestionWidget
    {
        private String QuestionName;
        private TextBlock QuestionLabelWidget = new TextBlock();
        private CheckBox QuestionInputWidget = new CheckBox();

        public BooleanQuestionWidget(string name)
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
            QuestionInputWidget.IsChecked = value.AsBool();
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
            QuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(QuestionName, new BoolValue(QuestionInputWidget.IsChecked.Value));
            QuestionInputWidget.Unchecked += (sender, args) => inputChanged.Invoke(QuestionName, new BoolValue(QuestionInputWidget.IsChecked.Value));
        }
    }
}
