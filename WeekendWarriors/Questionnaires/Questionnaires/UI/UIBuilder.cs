using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QLS.AST;
using Questionnaires.RunTime;
using Questionnaires.RunTime.DocumentModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.UI
{
    public class UIBuilder
    {
        private DocumentModel DocumentModel = new DocumentModel();
        private List<RunTime.Question> Questions = new List<RunTime.Question>();
        private List<Action<ExpressionEvaluator>> Rules = new List<Action<ExpressionEvaluator>>();

        public Result CreateInterface(string qlInput, string qlsInput, bool useStyling)
        {
            ClearMembersForProcessing();
            var result = new Result();
            
            try
            {
                return ProcessInputAndBuildUI(qlInput, qlsInput, useStyling, result);
            }
            catch (ParseException)
            {
                return result;
            }
        }

        private Result ProcessInputAndBuildUI(string qlInput, string qlsInput, bool useStyling, Result result)
        {
            ProcessQL(qlInput, result);
            ProcessQLS(qlsInput, useStyling, result);
            BuildQuestionnaireUI();
            return result;
        }

        private void BuildQuestionnaireUI()
        {
            var QuestionnaireBuilder = new QuestionnaireBuilder(Questions, Rules, DocumentModel);
            QuestionnaireBuilder.Build();
        }

        private void ProcessQL(string qlInput, Result result)
        {
            var FormAST = CreateFormAST(qlInput, result);
            var qlProcessor = new QL.Processing.Processor(Questions, Rules, DocumentModel);
            qlProcessor.Process(FormAST);
        }

        private Form CreateFormAST(string qlInput, Result result)
        {
            var FormAST = BuildFormAST(result, qlInput);
            AnalyzeFormAST(result, FormAST);
            return FormAST;
        }

        private void ProcessQLS(string qlsInput, bool useStyling, Result result)
        {
            if (useStyling)
            {
                var stylesheetAST = BuildStylesheetAST(result, qlsInput);
                AnalyzeStylesheet(result, Questions, stylesheetAST);
                QLS.Processing.Processor qlsProcessor = new QLS.Processing.Processor(Questions, DocumentModel);
                qlsProcessor.Process(stylesheetAST);
            }
        }

        private StyleSheet BuildStylesheetAST(Result result, string qlsInput)
        {
            var qlsAstBuilder = new QLS.AST.ASTBuilder(result);
            var stylesheetAST = qlsAstBuilder.BuildStylesheet(qlsInput);
            return stylesheetAST;
        }

        private QL.AST.Form BuildFormAST(Result result, string qlInput)
        {
            var qlAstBuilder = new QL.AST.ASTBuilder(result);
            return qlAstBuilder.BuildForm(qlInput);
        }

        private void AnalyzeFormAST(Result result, Form formAST)
        {
            if (result.ContainsErrors())
                return;

            var semanticAnalyzer = new SemanticAnalyzer(result);
            semanticAnalyzer.AnalyzeForm(formAST);

            if (result.ContainsErrors())
                throw new ParseException();
        }

        private void AnalyzeStylesheet(Result result, List<RunTime.Question> questions, StyleSheet stylesheetAST)
        {
            var semanticAnalyzerQLS = new QLS.SemanticAnalysis.Analyzer(result, questions);
            var semanticMessages = semanticAnalyzerQLS.Analyze(stylesheetAST);
            if (result.ContainsErrors())
                throw new ParseException();
        }

        private void ClearMembersForProcessing()
        {
            DocumentModel.Clear();
            Questions.Clear();
            Rules.Clear();
        }
    }
}
