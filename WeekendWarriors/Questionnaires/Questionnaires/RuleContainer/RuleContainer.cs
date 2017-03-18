using Questionnaires.RunTime;
using System;
using System.Collections.Generic;

namespace Questionnaires.RuleContainer
{
    using Rule = Action<QuestionStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private QuestionStore QuestionStore;  
        private Renderer.Renderer Renderer;
        private ExpressionEvaluator.Evaluator ExpresionEvaluator;

        public RuleContainer(QuestionStore questionStore, Renderer.Renderer renderer, ExpressionEvaluator.Evaluator expresionEvaluator)
        {
            QuestionStore = questionStore;
            Renderer = renderer;
            ExpresionEvaluator = expresionEvaluator;
        }

        public void AddRule(Rule rule)
        {
            Rules.Add(rule);
        }

        public void ApplyRules()
        {
            foreach (var rule in Rules)
            {
                rule(QuestionStore, Renderer, ExpresionEvaluator);
            }
        }
    }
}
