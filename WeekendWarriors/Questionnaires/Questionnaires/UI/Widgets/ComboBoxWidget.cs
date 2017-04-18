using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class ComboBoxWidget : QuestionWidget
    {
        private ComboBox ComboBox;

        public ComboBoxWidget() : base(new ComboBox())
        {
            Debug.Assert(Control.GetType() == typeof(ComboBox));
            ComboBox = Control as ComboBox;

            ComboBox.Items.Add("Yes");
            ComboBox.Items.Add("No");

            ComboBox.SelectedValue = "No";

            ComboBox.SelectionChanged += (sender, args) =>
            {
                var value = (sender as ComboBox).SelectedItem as string == "Yes";
                OnInputChanged(new BooleanType(value));
            };
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(BooleanType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(BooleanType value)
        {
            if (value.GetValue())
            {
                ComboBox.SelectedValue = "Yes";
            }
            else
            {
                ComboBox.SelectedValue = "No";
            }
        }
    }
}
