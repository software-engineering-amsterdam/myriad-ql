using Questionnaires.UI.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    public class DropDown : Widget
    {
        public override QuestionWidget CreateWidget(Types.BooleanType type)
        {
            return new ComboBoxWidget();
        }
    }
}
