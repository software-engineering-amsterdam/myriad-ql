using Questionnaires.QL.AST.Types;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class IntegerSliderWidget : QuestionWidget
    {
        private Slider Slider;

        public IntegerSliderWidget() : base(new Slider())
        {
            Debug.Assert(Control.GetType() == typeof(Slider));
            Slider = Control as Slider;

            Slider.TickFrequency = 1;
            Slider.IsSnapToTickEnabled = true;
            Slider.Maximum = 1E6;
            Slider.Minimum = 0;
            Slider.Width = 100;

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
