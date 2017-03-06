using Questionnaires.QL.AST;
using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
{
    public class QLContext
    {
        private Dictionary<string, IType> Context = new Dictionary<string, IType>();

        public void AddQuestion(string name, IType type)
        {
            Context.Add(name, type);
        }

        public IType GetQuestionType(string name)
        {            
            return Context[name];
        }

        public bool ContainsQuestion(string name)
        {
            return Context.ContainsKey(name);
        }
    }
}
