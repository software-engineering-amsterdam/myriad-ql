using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Question
{
    interface IQuestion
    {
        string Name { get; }
        string Body { get; }
        bool Visible { get; }
        IValue Value { get; }
    };
}
