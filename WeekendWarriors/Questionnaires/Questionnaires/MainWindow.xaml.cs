using Questionnaires.QL.AST;
using Questionnaires.QLS.AST;
using Questionnaires.Renderer.Containers;
using Questionnaires.RunTime;
using System;
using System.Collections.Generic;
using System.Windows;

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
                List<RunTime.Question> Questions = new List<RunTime.Question>();
                List<Action<QuestionStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> Rules = new List<Action<QuestionStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>>();
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
            catch (Questionnaires.Compilation.ParseException)
            {
                PrintMessages(result);
            }
            catch (Exception)
            {
                PrintMessages(result);
            }
        }

        private static void AnalyzeStylesheet(Compilation.Result result, List<RunTime.Question> Questions, StyleSheet stylesheetAST)
        {
            var semanticAnalyzerQLS = new QLS.SemanticAnalysis.Analyzer(result, Questions);
            var semanticMessages = semanticAnalyzerQLS.Analyze(stylesheetAST);
            if (result.ContainsErrors())
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
            if (result.ContainsErrors())
                return;

            var semanticAnalyzer = new SemanticAnalysis.SemanticAnalyzer(result);
            semanticAnalyzer.AnalyzeForm(FormAST);

            if (result.ContainsErrors())
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
