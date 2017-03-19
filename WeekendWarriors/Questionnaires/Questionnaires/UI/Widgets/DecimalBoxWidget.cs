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
    class DecimalBoxWidget : BoxWidget
    {
        public DecimalBoxWidget() : base(new TextBox())
        {
            TextBox.TextChanged += (sender, args) => OnInputChanged(new MoneyType(decimal.Parse(TextBox.Text)));
        }

        public override void SetQuestionValue(IType value)
        {
            Debug.Assert(value.GetType() == typeof(MoneyType));
            SetQuestionValue((dynamic)value);
        }

        public void SetQuestionValue(MoneyType value)
        {
            TextBox.Text = value.GetValue().ToString();
        }
    }
}
