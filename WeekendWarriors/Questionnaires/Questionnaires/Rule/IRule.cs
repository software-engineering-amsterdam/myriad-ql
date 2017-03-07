using Questionnaires.Renderer;
using Questionnaires.VariableStore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Rule
{
    interface IRule
    {
        void Apply(IVariableStore variableStore, Renderer.Renderer renderer);
    }
}
