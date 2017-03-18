using System;
using System.Collections.Generic;

namespace Questionnaires.RunTime
{
    class QuestionnaireBuilder
    {
        private DocumentModel.DocumentModel DocumentModel;
        private List<RunTime.Question> Questions = new List<RunTime.Question>();
        private List<Action<ExpressionEvaluator>> Rules = new List<Action<ExpressionEvaluator>>();

        public QuestionnaireBuilder(List<RunTime.Question> questions, List<Action<ExpressionEvaluator>> rules, DocumentModel.DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;
        }

        public void Build()
        {
            // Create the run-time objects 
            var questionStore = new QuestionStore(Questions);
            var renderer = new UI.Renderer();
            var expressionEvaluator = new ExpressionEvaluator(questionStore);
            var ruleContainer = new RuleContainer.RuleContainer(expressionEvaluator, Rules);

            renderer.RenderModel(DocumentModel);         
            ConntectRunTimeObjects(questionStore, ruleContainer);

            // Kick off initialization
            ruleContainer.ApplyRules();
        }

        private static void ConntectRunTimeObjects(QuestionStore questionStore, RuleContainer.RuleContainer ruleContainer)
        {
            questionStore.VariableChanged += (sender, args) =>
            {
                ruleContainer.ApplyRules();
            };
        }
    }
}
