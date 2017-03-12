using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    public class Question : INode
    {
        public string Name { get; }
        public Question(string name)
        {
            this.Name = name;
        }

    }
}
