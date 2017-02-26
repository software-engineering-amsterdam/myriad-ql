using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Rule;

namespace Questionnaires.RuleContainer
{
    class RuleContainer : IRuleContainer
    {
        private List<Rule.IRule> Rules = new List<Rule.IRule>();
         
        public void AddRule(IRule rule)
        {
            Rules.Add(rule);
        }

        public void ApplyRules(VariableStore.VariableStore variableStore, Renderer.Renderer renderer)
        {
            foreach (var rule in Rules.ToList())
            {
                rule.Apply(variableStore, renderer);
            }
        }
    }
}
