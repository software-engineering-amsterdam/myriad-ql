using Questionnaires.QL.AST.Types;
using System;
using System.Diagnostics;
using System.Windows.Controls;
using System.Windows.Media;

namespace Questionnaires.UI.Widgets
{
    class DecimalBoxWidget : BoxWidget
    {
        public DecimalBoxWidget() : base(new TextBox())
        {
            try
            {
                OnInputChanged(new MoneyType(decimal.Parse(TextBox.Text)));
                TextBox.Background = Brushes.White;
            }
            catch (FormatException)
            {
                TextBox.Background = Brushes.Red;
            }
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
