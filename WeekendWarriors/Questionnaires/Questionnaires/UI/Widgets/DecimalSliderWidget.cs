using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    public class DecimalSliderWidget : SliderWidget
    {
        public DecimalSliderWidget() : base(new Slider())
        {
            Slider.ValueChanged += (sender, args) => OnInputChanged(new MoneyType((decimal)Slider.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(MoneyType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            Slider.Value = (double)value.GetValue();
        }
    }
}
