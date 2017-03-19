using Questionnaires.QL.AST.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    public class IntegerSliderWidget : SliderWidget
    {
        public IntegerSliderWidget() : base(new Slider())
        {
            Slider.TickFrequency = 1;
            Slider.IsSnapToTickEnabled = true;

            Slider.ValueChanged += (sender, args) => OnInputChanged(new IntegerType((int)Slider.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(IntegerType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(IntegerType value)
        {
            Slider.Value = (int)value.GetValue();
        }
    }
}
