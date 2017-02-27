using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Renderer;
using Questionnaires.VariableStore;
using Questionnaires.AST;

namespace Questionnaires.Rule
{
    class Rule : IRule
    {
        private Action<IVariableStore, Renderer.Renderer> ActionRule;

        public Rule(Action<IVariableStore, Renderer.Renderer> rule)
        {
            ActionRule = rule;
        }

        public void Apply(IVariableStore variableStore, Renderer.Renderer renderer)
        {
            ActionRule.Invoke(variableStore, renderer);
        }
    }
}
