using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class TextBoxWidget : BoxWidget
    {
        public TextBoxWidget() : base(new TextBox())
        {
            TextBox.TextChanged += (sender, args) => OnInputChanged(new StringType(TextBox.Text));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(StringType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(StringType value)
        {
            TextBox.Text = value.GetValue();
        }
    }
}
