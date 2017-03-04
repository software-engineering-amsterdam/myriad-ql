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
        public string Name { get; set; }
        public string Body { get; set; }
        public bool Visible { get; set; }
        public IValue Value { get; set; }
    }
}
