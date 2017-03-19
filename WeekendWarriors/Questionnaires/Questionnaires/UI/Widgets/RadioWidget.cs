using Questionnaires.QL.AST.Types;
using System.Diagnostics;

namespace Questionnaires.UI.Widgets
{
    public class RadioWidget : QuestionWidget
    {
        private BinaryRadioGroup Buttons;

        public RadioWidget() : base(new BinaryRadioGroup("Yes", "No"))
        {
            Debug.Assert(Control.GetType() == typeof(BinaryRadioGroup));
            Buttons = Control as BinaryRadioGroup;
            Buttons.ValueChanged += (sender, args) => OnInputChanged(new BooleanType(Buttons.GetValue()));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(BooleanType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(BooleanType value)
        {
            Buttons.SetValue(value.GetValue());
        }
    }
}
