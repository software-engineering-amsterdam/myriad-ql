using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Question
{
    public enum Visibility
    {
        Visible,
        Hidden
    }

    public enum QuestionType
    {
        Question,
        ComputedQuestion
    }

    interface IQuestion
    {
        string Name { get; }
        string Body { get; }
        QuestionType Type { get; }
        Visibility Visibility { get; }
        IValue Value { get; }
    };
}
