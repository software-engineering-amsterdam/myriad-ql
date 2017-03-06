using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class RadioWidget : QuestionWidget
    {
        private String QuestionName;
        private TextBlock QuestionLabelWidget = new TextBlock();
        private RadioButton FirstQuestionInputWidget = new RadioButton();
        private RadioButton SecondQuestionInputWidget = new RadioButton();

        public RadioWidget(string name)
            : base()
        {
            QuestionName = name;
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
            FirstQuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(QuestionName, new BooleanType(true));
            SecondQuestionInputWidget.Checked += (sender, args) => inputChanged.Invoke(QuestionName, new BooleanType(false));
        }
    }
}
