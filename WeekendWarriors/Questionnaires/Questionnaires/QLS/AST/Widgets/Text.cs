using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Text : Widget
    {
        public override QuestionWidget CreateWidget(StringType type)
        {
            return new TextBoxWidget();
        }

        public override QuestionWidget CreateWidget(IntegerType type)
        {
            return new IntegerBoxWidget();
        }

        public override QuestionWidget CreateWidget(MoneyType type)
        {
            return new DecimalBoxWidget();
        }
    }
}
