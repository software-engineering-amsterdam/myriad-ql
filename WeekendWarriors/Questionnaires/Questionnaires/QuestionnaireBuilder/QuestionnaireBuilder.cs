using System;
using System.Collections.Generic;
using Questionnaires.RunTime;
using Questionnaires.Renderer.Containers;

namespace Questionnaires.QuestionnaireBuilder
{
    class QuestionnaireBuilder
    {
        private DocumentModel DocumentModel;
        private List<RunTime.Question> Questions = new List<RunTime.Question>();
        private List<Action<ExpressionEvaluator.Evaluator>> Rules = new List<Action<ExpressionEvaluator.Evaluator>>();

        public QuestionnaireBuilder(List<RunTime.Question> questions, List<Action<ExpressionEvaluator.Evaluator>> rules, DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;
        }

        public void Build()
        {
            // Create the run-time objects 
            var questionStore = new QuestionStore(Questions);
            var renderer = new Renderer.Renderer();
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(questionStore);
            var ruleContainer = new RuleContainer.RuleContainer(questionStore, renderer, expressionEvaluator);

            renderer.RenderModel(DocumentModel);
            AddRulesToRuleContainer(ruleContainer);            
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

        private void AddRulesToRuleContainer(RuleContainer.RuleContainer ruleContainer)
        {
            foreach (var rule in Rules)
            {
                ruleContainer.AddRule(rule);
            }
        }
    }
}
