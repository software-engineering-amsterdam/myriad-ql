using Questionnaires.RunTime;
using System;
using System.Collections.Generic;

namespace Questionnaires.RuleContainer
{
    using Rule = Action<ExpressionEvaluator.Evaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private ExpressionEvaluator.Evaluator ExpresionEvaluator;

        public RuleContainer(QuestionStore questionStore, Renderer.Renderer renderer, ExpressionEvaluator.Evaluator expresionEvaluator)
        {
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
                rule(ExpresionEvaluator);
            }
        }
    }
}
