using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Radio : Widget
    {
        public override QuestionWidget CreateWidget(Types.BooleanType type)
        {
            return new RadioWidget();
        }
    }
}
