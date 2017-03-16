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
using System.Threading;
using Questionnaires.Renderer.Style;
using Questionnaires.QLS.AST;
using Questionnaires.Renderer.Containers;

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
            bool useStyling = !String.IsNullOrEmpty(InputQLS.Text);
            ClearOutputWindow();

            Compilation.Result result = new Compilation.Result();

            // TODO: the way we use exceptions for flow control here is bad.
            try
            {
                var FormAST = BuildFormAST(result);
                AnalyzeFormAST(result, FormAST);

                /* 'Flatten' the AST*/
                DocumentModel DocumentModel = new DocumentModel();
                List<QL.AST.Question> Questions = new List<QL.AST.Question>();
                List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> Rules = new List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>>();
                QL.Processing.Processor qlProcessor = new QL.Processing.Processor(Questions, Rules, DocumentModel);
                qlProcessor.Process(FormAST);
                StyleSheet stylesheetAST = null;
                if (useStyling)
                {
                    stylesheetAST = BuildStylesheetAST(result);
                    AnalyzeStylesheet(result, Questions, stylesheetAST);
                    QLS.Processing.Processor qlsProcessor = new QLS.Processing.Processor(Questions, DocumentModel);
                    qlsProcessor.Process(stylesheetAST);
                }

                var QuestionnaireBuilder = new QuestionnaireBuilder.QuestionnaireBuilder(Questions, Rules, DocumentModel);
                QuestionnaireBuilder.Build();
               
            }
            catch(Questionnaires.Compilation.ParseException)
            {
                PrintMessages(result);
            }
            catch (Exception)
            {
                PrintMessages(result);
            }
        }

        private static void AnalyzeStylesheet(Compilation.Result result, List<QL.AST.Question> Questions, StyleSheet stylesheetAST)
        {
            var semanticAnalyzerQLS = new QLS.SemanticAnalysis.Analyzer(result, Questions);
            var semanticMessages = semanticAnalyzerQLS.Analyze(stylesheetAST);
            if (result.IsError())
                throw new Exception();
        }

        private StyleSheet BuildStylesheetAST(Compilation.Result result)
        {
            var qlsAstBuilder = new QLS.AST.ASTBuilder(result);
            var stylesheetAST = qlsAstBuilder.BuildStylesheet(InputQLS.Text);
            return stylesheetAST;
        }

        private void ClearOutputWindow()
        {
            Output.Text = "";
        }

        private static void AnalyzeFormAST(Compilation.Result result, Form FormAST)
        {
            if (result.IsError())
                return;

            var semanticAnalyzer = new SemanticAnalysis.SemanticAnalyzer(result);
            semanticAnalyzer.AnalyzeForm(FormAST);

            if (result.IsError())
                throw new Exception();
        }

        private QL.AST.Form BuildFormAST(Compilation.Result result)
        {            
            var qlAstBuilder = new QL.AST.ASTBuilder(result);
            return qlAstBuilder.BuildForm(InputQL.Text);
        }

        private void PrintMessages(Compilation.Result result)
        {
            foreach (var message in result.Events)
                Output.Text += message.ToString() + '\n';
        }
    }
}
