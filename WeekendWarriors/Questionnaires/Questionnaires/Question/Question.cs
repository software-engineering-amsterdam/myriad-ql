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
        public string Body { get; set; }
        public string Name { get; set; }
        public QuestionType Type { get; set; }
        public IValue Value { get; set; }
        public Questionnaires.Question.Visibility Visibility { get; set; }
    }
}
