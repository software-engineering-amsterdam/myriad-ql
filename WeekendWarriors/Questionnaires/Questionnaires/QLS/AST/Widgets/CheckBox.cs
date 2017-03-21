using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class CheckBox : Widget
    {
        public override QuestionWidget CreateWidget(BooleanType type)
        {
            return new CheckBoxWidget();
        }
    }
}
