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
    class IntegerPickerWidget : QuestionWidget
    {
        private IntegerUpDown Spinbox;

        public IntegerPickerWidget() : base(new IntegerUpDown())
        {
            Spinbox = Control as IntegerUpDown;
            Spinbox.AllowTextInput = false;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(IntegerType value)
        {
            Spinbox.Text = value.GetValue().ToString();
        }

        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            Spinbox.ValueChanged += (sender, args) => inputChanged.Invoke(this, new IntegerType(Spinbox.Value.Value));
        }
    }
}
