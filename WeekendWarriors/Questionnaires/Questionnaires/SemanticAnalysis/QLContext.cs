using Questionnaires.AST;
using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
{
    public class QLContext
    {
        private Dictionary<string, IValue> Context = new Dictionary<string, IValue>();

        public void AddQuestion(string name, IValue type)
        {
            Context.Add(name, type);
        }

        public IValue GetQuestionType(string name)
        {            
            return Context[name];
        }

        public bool ContainsQuestion(string name)
        {
            return Context.ContainsKey(name);
        }
    }
}
