﻿using System;
using System.Collections.Generic;
using Questionnaires.RunTime;
using Questionnaires.Renderer.Containers;

namespace Questionnaires.QuestionnaireBuilder
{
    class QuestionnaireBuilder 
    {
        private DocumentModel DocumentModel;
        private List<RunTime.Question> Questions = new List<RunTime.Question>();
        private List<Action<VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> Rules = new List<Action<VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>>();

        public QuestionnaireBuilder(List<RunTime.Question> questions, List<Action<VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> rules, DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;    
        }

        public void Build()
        {
            // Create the run-time objects 
            var variableStore = new VariableStore();
            var renderer = new Renderer.Renderer();
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(variableStore);
            var ruleContainer = new RuleContainer.RuleContainer(variableStore, renderer, expressionEvaluator);

            foreach (var question in Questions)
            {
                variableStore.AddQuestion(question);
            }

            renderer.RenderModel(DocumentModel);

            foreach (var rule in Rules)
            {
                ruleContainer.AddRule(rule);
            }

            // Connect the runtime objects
            variableStore.VariableChanged += (sender, args) =>
            {
                ruleContainer.ApplyRules();
            };

            // Kick off initialization
            ruleContainer.ApplyRules();
        }  
        
    }
}
