using Questionnaires.QL.AST.Types;
using System.Windows;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class SliderWidget : QuestionWidget
    {
        protected Slider Slider;

        public SliderWidget(Slider control) : base(control)
        {
            Slider = control;
            Slider.Maximum = 1E6;
            Slider.Minimum = 0;
            Slider.Width = 100;
        }

        public override void SetQuestionValue(IType value)
        { }
    }
}
