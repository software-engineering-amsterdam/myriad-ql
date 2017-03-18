using Questionnaires.UI.Widgets;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST.Widgets
{
    public class Spinbox : Widget
    {
        public override QuestionWidget CreateWidget(IntegerType type)
        {
            return new IntegerPickerWidget();
        }

        public override QuestionWidget CreateWidget(MoneyType type)
        {
            return new DecimalPickerWidget();
        }
    }
}
