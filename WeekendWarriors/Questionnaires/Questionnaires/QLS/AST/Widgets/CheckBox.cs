using Questionnaires.Renderer.Widgets;
using Questionnaires.Types;

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
