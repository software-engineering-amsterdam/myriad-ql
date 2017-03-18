using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Slider : Widget
    {
        // TODO: Add support to slider for different types

        public override QuestionWidget CreateWidget(IntegerType type)
        {
            return new SliderWidget();
        }

        public override QuestionWidget CreateWidget(MoneyType type)
        {
            return new SliderWidget();
        }
    }
}
