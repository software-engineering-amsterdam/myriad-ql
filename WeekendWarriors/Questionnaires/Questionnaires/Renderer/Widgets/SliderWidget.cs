using Questionnaires.Types;
using System.Diagnostics;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class SliderWidget : QuestionWidget
    {
        private Slider Slider;

        public SliderWidget() : base(new Slider())
        {
            Debug.Assert(Control.GetType() == typeof(Slider));
            Slider = Control as Slider;

            Slider.Maximum = 1E6;
            Slider.Minimum = 0;
            Slider.Width = 100;

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
