using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.VariableStore;

namespace Questionnaires.RuleContainer
{
    using Rule = Action<VariableStore.VariableStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private VariableStore.VariableStore VariableStore;  // TODO I feel that we have a namespace issue here. VariableStore.VariableStore, Renderer.Renderer.... Seems a bit redundant...
        private Renderer.Renderer Renderer;
        private ExpressionEvaluator.Evaluator ExpresionEvaluator;

        public RuleContainer(VariableStore.VariableStore variableStore, Renderer.Renderer renderer, ExpressionEvaluator.Evaluator expresionEvaluator)
        {
            this.VariableStore = variableStore;
            this.Renderer = renderer;
            this.ExpresionEvaluator = expresionEvaluator;
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
