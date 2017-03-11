using Questionnaires.QL.AST;
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
using Questionnaires.Types;
//using Questionnaires.QL.QuestionaireBuilder;
using System.Threading;
using Questionnaires.Renderer.Style;

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

            var astFactory = new QL.AST.ASTFactory();
            var form = astFactory.CreateForm(InputQL.Text);
            var semanticAnalyzer = new SemanticAnalysis.SemanticAnalyzer();
            var analysisResult = semanticAnalyzer.AnalyzeForm(form);
            foreach (var analysisEvent in analysisResult.Events)
                Output.Text += analysisEvent.ToString() + '\n';

            var qlsFactory = new QLS.AST.ASTBuilder();
            var Stylesheet = qlsFactory.Build(InputQLS.Text);

            TestNewSetupMethod(form, Stylesheet);

            // Testing from here
            //var variableStore = new VariableStore.VariableStore();
            //var renderer = new Renderer.Renderer(variableStore);
            //var ruleContainer = new RuleContainer.RuleContainer();
            //var questions = new Dictionary<string, Question>();
            //var questionBuilder = new QuestionnaireBuilder(variableStore, renderer, ruleContainer, questions);
            //questionBuilder.Build((Form)form); // Can casting to form here cause problems?
            //QLS.Processing.Processor something = new QLS.Processing.Processor(questions);
            //something.Process((dynamic)Stylesheet);

            //foreach (var question in questions.Values)
            //    renderer.AddQuestion(question, new WidgetStyle());

            //ruleContainer.ApplyRules(variableStore, renderer);
        }

        // TODO: this is what should be in the questionnaire builder
        private void TestNewSetupMethod(Form form, QLS.AST.StyleSheet styleSheet)
        {
            var questions = new List<Question>();
            var rules = new List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>>();
            QL.Processing.Processor qlProcessor = new QL.Processing.Processor(questions, rules);
            qlProcessor.Process(form);

            QLS.Processing.Processor qlsProcessor = new QLS.Processing.Processor(questions);
            qlsProcessor.Process(styleSheet);

            var variableStore = new VariableStore.VariableStore();
            var renderer = new Renderer.Renderer(variableStore);
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(variableStore);
            var ruleContainer = new RuleContainer.RuleContainer(variableStore, renderer, expressionEvaluator);

            foreach(var question in questions)
            {
                variableStore.SetValue(question.Identifier, question.Type);
                renderer.AddQuestion(question, new WidgetStyle());
            }

            foreach(var rule in rules)
            {
                ruleContainer.AddRule(rule);
            }

            // Connect the runtime objects
            variableStore.VariableChanged += (sender, args) => 
            {
                renderer.SetValue(args.Name, args.Value);
                ruleContainer.ApplyRules();
            };

            // Kick off initialization
            ruleContainer.ApplyRules();
        }
    }
}
