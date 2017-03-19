using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using Xceed.Wpf.Toolkit;

namespace Questionnaires.UI.Widgets
{
    public class IntegerPickerWidget : QuestionWidget
    {
        private IntegerUpDown Spinbox;

        public IntegerPickerWidget() : base(new IntegerUpDown())
        {
            Debug.Assert(Control.GetType() == typeof(IntegerUpDown));
            Spinbox = Control as IntegerUpDown;
            Spinbox.AllowTextInput = false;
            Spinbox.ValueChanged += (sender, args) => OnInputChanged(new IntegerType(Spinbox.Value.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(IntegerType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(IntegerType value)
        {
            Spinbox.Text = value.GetValue().ToString();
        }
    }
}
