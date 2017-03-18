using Questionnaires.Types;
using System.Diagnostics;
using Xceed.Wpf.Toolkit;

namespace Questionnaires.UI.Widgets
{
    class DecimalPickerWidget : QuestionWidget
    {
        private DecimalUpDown SpinBox;

        public DecimalPickerWidget() : base(new DecimalUpDown())
        {
            Debug.Assert(Control.GetType() == typeof(DecimalUpDown));
            SpinBox = Control as DecimalUpDown;
            SpinBox.AllowTextInput = false;
            SpinBox.ValueChanged += (sender, args) => OnInputChanged(new MoneyType(SpinBox.Value.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(MoneyType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            SpinBox.Text = value.GetValue().ToString();
        }
    }
}
