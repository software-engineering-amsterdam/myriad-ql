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

        public QuestionnaireBuilder(List<QL.AST.Question> questions, List<Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> rules, DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;    
        }

        public void Build()
        {
            // Create the run-time objects 
            var variableStore = new VariableStore.VariableStore();
            var renderer = new Renderer.Renderer(variableStore);
            var expressionEvaluator = new ExpressionEvaluator.Evaluator(variableStore);
            var ruleContainer = new RuleContainer.RuleContainer(variableStore, renderer, expressionEvaluator);

            foreach (var question in Questions)
            {
                variableStore.SetValue(question.Identifier, question.Type);
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
