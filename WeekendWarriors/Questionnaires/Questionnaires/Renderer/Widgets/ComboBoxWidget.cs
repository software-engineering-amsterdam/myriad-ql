using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class ComboBoxWidget : QuestionWidget
    {
        private String QuestionName;
        private TextBlock QuestionLabelWidget = new TextBlock();
        private ComboBox QuestionInputWidget = new ComboBox();

        public ComboBoxWidget()
            : base()
        {
            Orientation = Orientation.Horizontal;
            Children.Add(QuestionLabelWidget);
            Children.Add(QuestionInputWidget);

            QuestionInputWidget.Items.Add("Yes");
            QuestionInputWidget.Items.Add("No");

            QuestionInputWidget.SelectedValue = "No";
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
                QuestionInputWidget.SelectedValue = "Yes";
            }
            else
            {
                QuestionInputWidget.SelectedValue = "No";
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
            // string text = (sender as ComboBox).SelectedItem as string;
            QuestionInputWidget.SelectionChanged += (sender, args) =>
            {
                bool value = (sender as ComboBox).SelectedItem as string == "Yes";
                inputChanged.Invoke(this, new BooleanType(value));
            };
        }
    }
}
