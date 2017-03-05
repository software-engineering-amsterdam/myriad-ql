using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.VariableStore;

namespace Questionnaires.RuleContainer
{
    using Rule = Action<IVariableStore, Renderer.Renderer>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
         
        public void AddRule(Rule rule)
        {
            Rules.Add(rule);
        }

        public void ApplyRules(VariableStore.VariableStore variableStore, Renderer.Renderer renderer)
        {
            foreach (var rule in Rules.ToList())
            {
                rule(variableStore, renderer);
            }
        }
    }
}
