using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Question
{
    class Question : IQuestion
    {
        public Question(string name, string body, IValue value)
        {
            this.Name = name;
            this.Body = body;
            this.Value = value;
        }
        public string Name { get;  }
        public string Body { get;  }
        public IValue Value { get;  }
    }
}
