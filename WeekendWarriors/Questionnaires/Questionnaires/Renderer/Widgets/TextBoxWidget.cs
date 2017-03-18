using Questionnaires.Types;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class TextBoxWidget : QuestionWidget
    {
        private TextBox TextBox;

        public TextBoxWidget() : base(new TextBox())
        {
            TextBox = Control as TextBox;
            TextBox.TextChanged += (sender, args) => OnInputChanged(new StringType(TextBox.Text));
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(StringType value)
        {
            TextBox.Text = value.GetValue();
        }
    }
}
