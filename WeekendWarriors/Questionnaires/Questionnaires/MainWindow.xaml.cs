using Questionnaires.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Questionnaires
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            string inputString = "form Box1HouseOwning { hasSoldHouse: \"Did you sell a house in 2010?\" boolean(a) }";

            var formFactory = new AST.ASTFactory();
            var parser = formFactory.CreateParser(inputString);
            var form = formFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Form);

            var semanticAnalyzer = new SemanticAnalysis.Analyzer();

            semanticAnalyzer.SemanticError += ReportSemanticError;
            semanticAnalyzer.Analyze(form);
        }

        private void ReportSemanticError(object sender, SemanticAnalysis.SemanticErrorArgs e)
        {
            textBlock.Text = textBlock.Text + "\n Semantic error: " + e.Message;
        }
    }
}
