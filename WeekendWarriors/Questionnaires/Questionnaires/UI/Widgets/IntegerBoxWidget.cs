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
    class IntegerBoxWidget : QuestionWidget
    {
        private TextBox TextBox;

        public IntegerBoxWidget() : base(new TextBox())
        {
            Debug.Assert(Control.GetType() == typeof(TextBox));
            TextBox = Control as TextBox;
            TextBox.TextChanged += (sender, args) => OnInputChanged(new IntegerType(int.Parse(TextBox.Text)));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(IntegerType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(IntegerType value)
        {
            TextBox.Text = value.GetValue().ToString();
        }
    }
}
