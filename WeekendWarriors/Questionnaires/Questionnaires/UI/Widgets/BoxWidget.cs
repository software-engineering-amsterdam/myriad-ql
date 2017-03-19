using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class BoxWidget : QuestionWidget
    {
        protected TextBox TextBox;

        public BoxWidget(TextBox textBox) : base(textBox)
        {
            TextBox = textBox;
        }

        public override void SetQuestionValue(IType value)
        {
        }

    }
}
