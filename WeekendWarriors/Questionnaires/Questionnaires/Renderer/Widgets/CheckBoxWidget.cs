using Questionnaires.Types;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class CheckBoxWidget : QuestionWidget
    {
        private CheckBox CheckBox;

        public CheckBoxWidget() : base(new CheckBox())
        {
            CheckBox = Control as CheckBox;
            CheckBox.Checked += (sender, args) => OnInputChanged(new BooleanType(CheckBox.IsChecked.Value));
            CheckBox.Unchecked += (sender, args) => OnInputChanged(new BooleanType(CheckBox.IsChecked.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(BooleanType value)
        {
            CheckBox.IsChecked = value.GetValue();
        }
    }
}
