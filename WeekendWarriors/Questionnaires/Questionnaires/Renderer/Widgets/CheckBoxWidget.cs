using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Types;
using System.Windows.Controls;
using System.Windows.Markup;
using System.Windows;
using Questionnaires.Renderer.Style;
using System.Windows.Media;

namespace Questionnaires.Renderer.Widgets
{
    class CheckBoxWidget : QuestionWidget
    {
        private CheckBox CheckBox;

        public CheckBoxWidget() : base(new CheckBox())
        {
            CheckBox = Control as CheckBox;
        }

        public override void SetQuestionValue(IType value)
        {
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(BooleanType value)
        {
            CheckBox.IsChecked = value.GetValue();
        }

        public override void SetOnInputChanged(Renderer.InputChangedCallback inputChanged)
        {
            CheckBox.Checked += (sender, args) => inputChanged.Invoke(this, new BooleanType(CheckBox.IsChecked.Value));
            CheckBox.Unchecked += (sender, args) => inputChanged.Invoke(this, new BooleanType(CheckBox.IsChecked.Value));
        }
    }
}
