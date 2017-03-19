using System;
using System.Windows;
using System.Windows.Controls;

namespace Questionnaires.UI.Widgets
{
    class BinaryRadioGroup : StackPanel
    {
        private RadioButton TrueButton = new RadioButton();
        private RadioButton FalseButton = new RadioButton();

        public BinaryRadioGroup(string trueLabel, string falseLabel)
        {
            TrueButton.Content = trueLabel;
            FalseButton.Content = falseLabel;
            Orientation = Orientation.Horizontal;
            Children.Add(TrueButton);
            Children.Add(FalseButton);

            TrueButton.Checked += Button_Checked;
            FalseButton.Checked += Button_Checked;
        }

        public void SetValue(bool value)
        {
            TrueButton.IsChecked = value;
            FalseButton.IsChecked = !TrueButton.IsChecked;
        }

        public bool GetValue()
        {
            return TrueButton.IsChecked == true;
        }

        private void Button_Checked(object sender, RoutedEventArgs e)
        {
            OnValueChanged(new EventArgs());
        }

        public event EventHandler ValueChanged;
        private void OnValueChanged(EventArgs e)
        {
            if (ValueChanged != null)
                ValueChanged(this, e);
        }
    }
}
