using Questionnaires.QL.AST.Visitor;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer;
using Questionnaires.RunTime;
using Questionnaires.Types;
using System.ComponentModel;
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
            var renderer = new Renderer.Renderer(variableStore);
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(variableStore);
            var ruleContainer = new RuleContainer.RuleContainer(variableStore, renderer, expressionEvaluator);

            foreach (var question in Questions)
            {
                variableStore.AddQuestion(question);
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
