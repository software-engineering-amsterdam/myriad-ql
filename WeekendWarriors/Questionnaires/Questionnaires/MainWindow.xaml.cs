using Questionnaires.AST;
using Questionnaires.Question;
using Questionnaires.Renderer;
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
using Questionnaires.Value;
using Questionnaires.QuestionaireBuilder;
using System.Threading;

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
            Output.Text = "";

            var formFactory = new AST.ASTFactory();
            var parser = formFactory.CreateParser(Input.Text);
            var form = formFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Form);            
            var semanticAnalyzer = new SemanticAnalysis.SemanticAnalyzer();
            var analysisResult = semanticAnalyzer.Analyze(form);
            foreach (var analysisEvent in analysisResult.Events)
                Output.Text += analysisEvent.ToString() + '\n';

            // Testing from here
            var variableStore = new VariableStore.VariableStore();
            var renderer = new Renderer.Renderer();
            var ruleContainer = new RuleContainer.RuleContainer();

            var questionBuilder = new QuestionnaireBuilder(variableStore, renderer, ruleContainer);
            questionBuilder.Visit((QLForm)form); // Can casting to form here cause problems?
            
            ruleContainer.ApplyRules(variableStore, renderer);
        }
    }
}
