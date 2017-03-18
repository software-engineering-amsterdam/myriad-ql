using Questionnaires.RunTime;
using System;
using System.Collections.Generic;

namespace Questionnaires.RuleContainer
{
    using Rule = Action<VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private VariableStore VariableStore;  // TODO I feel that we have a namespace issue here. VariableStore, Renderer.Renderer.... Seems a bit redundant...
        private Renderer.Renderer Renderer;
        private ExpressionEvaluator.Evaluator ExpresionEvaluator;

        public RuleContainer(VariableStore variableStore, Renderer.Renderer renderer, ExpressionEvaluator.Evaluator expresionEvaluator)
        {
            VariableStore = variableStore;
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
                rule(VariableStore, Renderer, ExpresionEvaluator);
            }
        }
    }
}
