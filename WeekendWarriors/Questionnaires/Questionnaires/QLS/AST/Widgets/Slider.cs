using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Slider : Widget
    {
        public override QuestionWidget CreateWidget(IntegerType type)
        {
            return new IntegerSliderWidget();
        }

        public override QuestionWidget CreateWidget(MoneyType type)
        {
            return new DecimalSliderWidget();
        }
    }
}
