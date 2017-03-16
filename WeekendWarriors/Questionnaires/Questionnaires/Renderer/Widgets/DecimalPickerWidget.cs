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
    class DecimalPickerWidget : QuestionWidget
    {
        private DecimalUpDown SpinBox;

        public DecimalPickerWidget() : base(new DecimalUpDown())
        {
            SpinBox = Control as DecimalUpDown;
            SpinBox.AllowTextInput = false;
            SpinBox.ValueChanged += (sender, args) => OnInputChanged(new MoneyType(SpinBox.Value.Value));
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            SpinBox.Text = value.GetValue().ToString();
        }
    }
}
