using Questionnaires.UI.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Slider : Widget
    {
        // TODO: Add support to slider for different types

        public override QuestionWidget CreateWidget(Types.IntegerType type)
        {
            return new SliderWidget();
        }

        public override QuestionWidget CreateWidget(Types.MoneyType type)
        {
            return new SliderWidget();
        }
    }
}
