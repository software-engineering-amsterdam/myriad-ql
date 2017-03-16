using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class RadioWidget : QuestionWidget
    {
        private BinaryRadioGroup Buttons;

        public RadioWidget() : base(new BinaryRadioGroup("Yes", "No"))
        {
            Buttons = Control as BinaryRadioGroup;
            Buttons.ValueChanged += (sender, args) => OnInputChanged(new BooleanType(Buttons.GetValue()));
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(BooleanType value)
        {
            Buttons.SetValue(value.GetValue());
        }
    }
}
