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
    class IntegerBoxWidget : BoxWidget
    {
        public IntegerBoxWidget() : base(new TextBox())
        {
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
