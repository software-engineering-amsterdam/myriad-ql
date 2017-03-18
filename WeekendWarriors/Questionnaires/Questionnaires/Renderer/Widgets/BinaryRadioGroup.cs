using System;
using System.Windows.Controls;

namespace Questionnaires.Renderer.Widgets
{
    class BinaryRadioGroup : StackPanel
    {
        private RadioButton trueButton = new RadioButton();
        private RadioButton falseButton = new RadioButton();

        public BinaryRadioGroup(string trueLabel, string falseLabel)
        {
            trueButton.Content = trueLabel;
            falseButton.Content = falseLabel;
            Orientation = Orientation.Horizontal;
            Children.Add(trueButton);
            Children.Add(falseButton);

            trueButton.Checked += Button_Checked;
            falseButton.Checked += Button_Checked;
        }

        public void SetValue(bool value)
        {
            trueButton.IsChecked = value;
            falseButton.IsChecked = !trueButton.IsChecked;
        }

        public bool GetValue()
        {
            return trueButton.IsChecked == true;
        }

        private void Button_Checked(object sender, System.Windows.RoutedEventArgs e)
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
