using Questionnaires.UI;
using System;
using System.Collections.Generic;

namespace Questionnaires.RunTime
{
    class QuestionnaireBuilder
    {
        private DocumentModel.DocumentModel DocumentModel;
        private List<Question> Questions = new List<Question>();
        private List<Action<ExpressionEvaluator>> Rules = new List<Action<ExpressionEvaluator>>();

        public QuestionnaireBuilder(List<Question> questions, List<Action<ExpressionEvaluator>> rules, DocumentModel.DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;
        }

        public void Build()
        {
            // Create the run-time objects 
            var questionStore = new QuestionStore(Questions);
            var renderer = new Renderer();
            var expressionEvaluator = new ExpressionEvaluator(questionStore);
            var ruleContainer = new RuleContainer(expressionEvaluator, Rules);

            renderer.RenderModel(DocumentModel);
            ConntectRunTimeObjects(questionStore, ruleContainer);

            // Kick off initialization
            ruleContainer.ApplyRules();
        }

        private static void ConntectRunTimeObjects(QuestionStore questionStore, RuleContainer ruleContainer)
        {
            questionStore.VariableChanged += (sender, args) =>
            {
                ruleContainer.ApplyRules();
            };
        }
    }
}
