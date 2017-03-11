using Questionnaires.QL.AST.Visitor;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer;
using Questionnaires.VariableStore;
using Questionnaires.Types;
using System.ComponentModel;
using Questionnaires.Renderer.Containers;

namespace Questionnaires.QuestionnaireBuilder
{
    class QuestionnaireBuilder 
    {
        private DocumentModel DocumentModel;
        private List<QL.AST.Question> Questions = new List<QL.AST.Question>();
        private List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> Rules = new List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>>();
        private QL.AST.Form Form;
        private QLS.AST.StyleSheet StyleSheet;

        public QuestionnaireBuilder(QL.AST.Form form, QLS.AST.StyleSheet stylesheet = null)
        {
            this.Form = form;
            this.StyleSheet = stylesheet;            
        }

        public void Build()
        {
            ProcessASTs();
            InstantiateRunTime();
        }

        // 'Flatten' the ASTs into lists of objects we can use to paramterize our run-time objects
        private void ProcessASTs()
        {
            // Buil up a list of questions and run-time rules from the QL input
            QL.Processing.Processor qlProcessor = new QL.Processing.Processor(Questions, Rules);
            qlProcessor.Process(Form);

            // Optionally style the questions based on the QLS input
            if (StyleSheet != null)
            {
                QLS.Processing.Processor qlsProcessor = new QLS.Processing.Processor(Questions);
                DocumentModel = qlsProcessor.Process(StyleSheet);
            }
            else
            {
                DocumentModel = new DocumentModel();
                DocumentModel.Pages = new List<Page>{ new Page() };
                DocumentModel.Pages[0].Name = "Form";
                DocumentModel.Pages[0].Questions = Questions;
            }
        }   

        private void InstantiateRunTime()
        {
            // Create the run-time objects 
            var variableStore = new VariableStore.VariableStore();
            var renderer = new Renderer.Renderer(variableStore);
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(variableStore);
            var ruleContainer = new RuleContainer.RuleContainer(variableStore, renderer, expressionEvaluator);

            foreach (var question in Questions)
            {
                variableStore.SetValue(question.Identifier, question.Type);
                //renderer.AddQuestion(question);
            }

            renderer.AddModel(DocumentModel);
            foreach (var rule in Rules)
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
