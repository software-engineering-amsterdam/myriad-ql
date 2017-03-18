using Questionnaires.Renderer.Widgets;

namespace Questionnaires.QLS.AST.Widgets
{
    class Spinbox : Widget
    {
        public override QuestionWidget CreateWidget(Types.IntegerType type)
        {
            return new IntegerPickerWidget();
        }

        public override QuestionWidget CreateWidget(Types.MoneyType type)
        {
            return new DecimalPickerWidget();
        }
    }
}
