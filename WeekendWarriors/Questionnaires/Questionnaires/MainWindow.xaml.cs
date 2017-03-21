using Questionnaires.QL.AST;
using Questionnaires.QLS.AST;
using Questionnaires.RunTime.DocumentModel;
using Questionnaires.RunTime;
using System;
using System.Collections.Generic;
using System.Windows;
using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.UI;

namespace Questionnaires
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Interpret_Click(object sender, RoutedEventArgs e)
        {
            bool useStyling = !string.IsNullOrEmpty(InputQLS.Text);
            ClearOutputWindow();

            PrintMessages(new UIBuilder().CreateInterface(InputQL.Text, InputQLS.Text, useStyling));
        }

        private void ClearOutputWindow()
        {
            Output.Text = "";
        }

        private void PrintMessages(Result result)
        {
            foreach (var message in result.Events)
                Output.Text += message.ToString() + '\n';
        }
    }
}
