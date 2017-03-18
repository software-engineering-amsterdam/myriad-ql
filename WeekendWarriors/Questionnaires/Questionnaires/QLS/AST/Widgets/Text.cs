using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Text : Widget
    {
        // TODO: Add support for different textbox widget types
        public override QuestionWidget CreateWidget(StringType type)
        {
            return new TextBoxWidget();
        }

        public override QuestionWidget CreateWidget(IntegerType type)
        {
            return new TextBoxWidget();
        }

        public override QuestionWidget CreateWidget(MoneyType type)
        {
            return new TextBoxWidget();
        }


    }
}
