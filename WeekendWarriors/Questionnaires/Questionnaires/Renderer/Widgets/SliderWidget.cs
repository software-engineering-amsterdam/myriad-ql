using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Xceed.Wpf.Toolkit;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class SliderWidget : QuestionWidget
    {
        private Slider Slider;

        public SliderWidget() : base(new Slider())
        {
            Slider = Control as Slider;

            Slider.Maximum = 1E6;
            Slider.Minimum = 0;
            Slider.Width = 100;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            Slider.Value = (double)value.GetValue();
        }

        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            Slider.ValueChanged += (sender, args) => inputChanged.Invoke(this, new MoneyType((decimal)Slider.Value));
        }
    }
}
