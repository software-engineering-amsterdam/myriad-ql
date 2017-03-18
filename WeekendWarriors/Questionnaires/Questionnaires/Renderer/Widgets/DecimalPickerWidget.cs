using Questionnaires.Types;
using Xceed.Wpf.Toolkit;

namespace Questionnaires.Renderer.Widgets
{
    class DecimalPickerWidget : QuestionWidget
    {
        private DecimalUpDown SpinBox;

        public DecimalPickerWidget() : base(new DecimalUpDown())
        {
            SpinBox = Control as DecimalUpDown;
            SpinBox.AllowTextInput = false;
            SpinBox.ValueChanged += (sender, args) => OnInputChanged(new MoneyType(SpinBox.Value.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            SpinBox.Text = value.GetValue().ToString();
        }
    }
}
