using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Text : Widget
    {
        // TODO: Add support for different textbox widget types
        public override QuestionWidget CreateWidget(Types.StringType type)
        {
            return new TextBoxWidget();
        }

        public override QuestionWidget CreateWidget(Types.IntegerType type)
        {
            return new TextBoxWidget();
        }

        public override QuestionWidget CreateWidget(Types.MoneyType type)
        {
            return new TextBoxWidget();
        }


    }
}
