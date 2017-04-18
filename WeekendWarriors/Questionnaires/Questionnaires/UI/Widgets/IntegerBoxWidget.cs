using Questionnaires.QL.AST.Types;
using System;
using System.Diagnostics;
using System.Windows.Controls;
using System.Windows.Media;

namespace Questionnaires.UI.Widgets
{
    public class IntegerBoxWidget : BoxWidget
    {
        public IntegerBoxWidget() : base(new TextBox())
        {
            TextBox.TextChanged += (sender, args) =>
            {
                try
                {
                    OnInputChanged(new IntegerType(int.Parse(TextBox.Text)));
                    TextBox.Background = Brushes.White;
                }
                catch (FormatException)
                {
                    TextBox.Background = Brushes.Red;
                }
            };
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
